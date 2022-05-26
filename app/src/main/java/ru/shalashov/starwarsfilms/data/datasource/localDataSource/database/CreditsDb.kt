package ru.shalashov.starwarsfilms.data.datasource.localDataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao.CreditsDao
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CreditsModel

@Database(entities = [CreditsModel::class], version = 1)
abstract class CreditsDb: RoomDatabase() {
    abstract fun creditsDao(): CreditsDao
}