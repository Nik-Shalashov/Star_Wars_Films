package ru.shalashov.starwarsfilms.data.datasource.localDataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao.CreditsDao
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CastModel
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CreditsModel
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.CrewModel

@Database(entities = [CreditsModel::class, CastModel::class, CrewModel::class], version = 1)
abstract class CreditsDb: RoomDatabase() {
    abstract fun creditsDao(): CreditsDao
}