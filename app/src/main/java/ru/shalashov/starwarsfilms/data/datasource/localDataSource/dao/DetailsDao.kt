package ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao

import androidx.room.*
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.DetailsModel

@Dao
interface DetailsDao {

    @Query("SELECT * FROM details WHERE id LIKE :id")
    suspend fun getDetailsById(id: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: DetailsModel)
}