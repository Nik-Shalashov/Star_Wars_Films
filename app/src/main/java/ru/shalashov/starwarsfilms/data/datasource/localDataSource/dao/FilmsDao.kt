package ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao

import androidx.room.*
import ru.shalashov.starwarsfilms.data.model.FilmsModel

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<FilmsModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: FilmsModel)
}