package ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CreditsModel
import ru.shalashov.starwarsfilms.domain.entities.Credits

@Dao
interface CreditsDao {

    @Query("SELECT * FROM credits WHERE id LIKE :id")
    suspend fun getCredits(id: Int): List<Credits>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity:CreditsModel)
}