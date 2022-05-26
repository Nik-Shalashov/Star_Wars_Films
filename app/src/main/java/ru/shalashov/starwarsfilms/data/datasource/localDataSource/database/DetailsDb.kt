package ru.shalashov.starwarsfilms.data.datasource.localDataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao.DetailsDao
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.DetailsModel
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.GenresModel

@Database(entities = [DetailsModel::class, GenresModel::class], version = 1)
abstract class DetailsDb: RoomDatabase() {
    abstract fun detailsDao(): DetailsDao
}