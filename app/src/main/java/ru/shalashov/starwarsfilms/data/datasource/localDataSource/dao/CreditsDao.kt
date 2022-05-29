package ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CreditsModel

@Dao
interface CreditsDao {

    @Query("SELECT * FROM credits WHERE id LIKE :id")
    suspend fun getCredits(id: Int): CreditsModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity:CreditsModel)
}