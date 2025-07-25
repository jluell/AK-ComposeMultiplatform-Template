import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.android.library)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(
                libs.versions.android.jvmTarget.map { version ->
                    JvmTarget.fromTarget(version)
                }
            )
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        browser()
    }
    jvm()
    applyDefaultHierarchyTemplate()
    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlinx.coroutines.DelicateCoroutinesApi")
                optIn("kotlin.io.encoding.ExperimentalEncodingApi")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
        commonMain.dependencies {
            api(libs.kotlinx.datetime)
            api(libs.bundles.ktor.common)
            api(libs.kotlinx.coroutines.core)
            api(libs.kotlinx.serialization.json)
            implementation(libs.cashapp.paging.common)
            implementation(libs.touchlab.stately.concurrent.collections)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.java)
        }
        val mobileAndDesktopMain by creating {
            dependsOn(commonMain.get())
            dependencies {
                implementation(libs.androidx.datastore.preferences)
            }
        }
        androidMain.get().dependsOn(mobileAndDesktopMain)
        iosMain.get().dependsOn(mobileAndDesktopMain)
        jvmMain.get().dependsOn(mobileAndDesktopMain)
    }
}

android {
    namespace = "shared.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility(libs.versions.android.jvmTarget.get())
        targetCompatibility(libs.versions.android.jvmTarget.get())
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
