package ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao

import androidx.room.*
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.ResultsModel

@Dao
interface FilmsDao {

    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<ResultsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ResultsModel)
}