package shared.data.repository

import shared.core.util.Result

/**
 * Base repository interface following Clean Architecture principles.
 * 
 * Repositories are part of the data layer and implement data source abstractions
 * defined in the domain layer. They handle data fetching, caching, and persistence.
 * 
 * @param T The domain model type
 * @param ID The identifier type for the model
 */
interface Repository<T, ID> {
    /**
     * Retrieves an item by its ID.
     * 
     * @param id The identifier of the item
     * @return Result containing the item or an error
     */
    suspend fun getById(id: ID): Result<T>
    
    /**
     * Retrieves all items.
     * 
     * @return Result containing a list of items or an error
     */
    suspend fun getAll(): Result<List<T>>
    
    /**
     * Saves an item.
     * 
     * @param item The item to save
     * @return Result containing the saved item or an error
     */
    suspend fun save(item: T): Result<T>
    
    /**
     * Deletes an item by its ID.
     * 
     * @param id The identifier of the item to delete
     * @return Result indicating success or failure
     */
    suspend fun delete(id: ID): Result<Unit>
}

/**
 * Base remote data source interface.
 * 
 * Remote data sources handle communication with backend APIs.
 * 
 * @param T The data transfer object (DTO) type
 * @param ID The identifier type
 */
interface RemoteDataSource<T, ID> {
    /**
     * Fetches data from a remote source.
     * 
     * @param id Optional identifier for fetching specific item
     * @return Result containing the data or an error
     */
    suspend fun fetch(id: ID? = null): Result<T>
    
    /**
     * Fetches a list of items from a remote source.
     * 
     * @return Result containing a list of items or an error
     */
    suspend fun fetchAll(): Result<List<T>>
}

/**
 * Base local data source interface.
 * 
 * Local data sources handle local storage (database, cache, etc.).
 * 
 * @param T The entity type
 * @param ID The identifier type
 */
interface LocalDataSource<T, ID> {
    /**
     * Retrieves an item from local storage.
     * 
     * @param id The identifier of the item
     * @return Result containing the item or an error
     */
    suspend fun get(id: ID): Result<T?>
    
    /**
     * Retrieves all items from local storage.
     * 
     * @return Result containing a list of items or an error
     */
    suspend fun getAll(): Result<List<T>>
    
    /**
     * Saves an item to local storage.
     * 
     * @param item The item to save
     * @return Result indicating success or failure
     */
    suspend fun save(item: T): Result<Unit>
    
    /**
     * Deletes an item from local storage.
     * 
     * @param id The identifier of the item to delete
     * @return Result indicating success or failure
     */
    suspend fun delete(id: ID): Result<Unit>
    
    /**
     * Clears all items from local storage.
     * 
     * @return Result indicating success or failure
     */
    suspend fun clear(): Result<Unit>
}

