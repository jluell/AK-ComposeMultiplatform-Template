import org.jetbrains.compose.reload.ComposeHotRun
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.hot.reload)
}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.android.jvmTarget.get()
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "App"
            isStatic = true
            linkerOpts.add("-lsqlite3")
        }
    }
    js(IR) {
        useEsModules()
        browser {
            commonWebpackConfig {
                outputFileName = "app.js"
            }
            useCommonJs()
        }
        binaries.executable()
    }
    jvm()
    applyDefaultHierarchyTemplate()
    sourceSets {
        all {
            languageSettings {
                optIn("kotlinx.coroutines.FlowPreview")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlinx.coroutines.DelicateCoroutinesApi")
                optIn("androidx.compose.ui.ExperimentalComposeUiApi")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("kotlinx.serialization.InternalSerializationApi")
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }
        commonMain.dependencies {
            implementation(compose.components.resources)
            implementation(libs.koin.core)
            implementation(libs.sqldelight.coroutines)
            implementation(projects.shared.data)
            implementation(projects.shared.domain)
            implementation(projects.shared.presentation)
        }
        androidMain.dependencies {
            implementation(libs.androidx.splashscreen)
            implementation(libs.sqldelight.android.driver)
        }
        iosMain.dependencies {
            implementation(libs.sqldelight.native.driver)
            implementation(libs.touchlab.stately.common)
            implementation(libs.touchlab.stately.isolate)
            implementation(libs.touchlab.stately.iso.collections)
        }
        jsMain.dependencies {
            implementation(libs.sqldelight.web.worker.driver)
            implementation(libs.touchlab.stately.iso.collections.js)
            implementation(npm("sql.js", "1.10.3"))
            implementation(npm("@cashapp/sqldelight-sqljs-worker", libs.versions.sqldelight.get()))
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.sqldelight.sqlite.driver)
        }
        val mobileAndDesktopMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.sqlite.bundled)
            }
        }
        androidMain.get().dependsOn(mobileAndDesktopMain)
        iosMain.get().dependsOn(mobileAndDesktopMain)
        jvmMain.get().dependsOn(mobileAndDesktopMain)
    }
}
sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("de.appkreativ.cmp.common.data.source.database.sqldelight")
            generateAsync.set(true)
        }
    }
}
android {
    namespace = "de.appkreativ.cmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "de.appkreativ.cmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        named("debug") {
            storeFile = file("assemble/android/debug.keystore")
            keyAlias = "androiddebugkey"
            storePassword = "android"
            keyPassword = "android"
        }
    }
    buildTypes {
        named("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
        named("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "assemble/proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(libs.versions.android.jvmTarget.get())
        targetCompatibility(libs.versions.android.jvmTarget.get())
    }
}
compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb
            )
            packageName = "de.appkreativ.cmp"
            packageVersion = "1.0.0"
            modules(
                "java.sql",
                "java.net.http",
                "jdk.unsupported",
                "jdk.security.auth",
            )
        }
        buildTypes.release.proguard {
            obfuscate.set(false)
            configurationFiles.from(project.file("assemble/proguard-rules.pro"))
        }
    }
}
tasks.register<ComposeHotRun>("runHot") {
    group = "compose desktop"
    mainClass.set("MainKt")
}
composeCompiler {
    featureFlags.add(ComposeFeatureFlag.OptimizeNonSkippingGroups)
}
