package ru.shalashov.starwarsfilms.data.datasource.localDataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao.FilmsDao
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.PopularFilmsModel
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.models.ResultsModel

@Database(entities = [ResultsModel::class], version = 1)
abstract class FilmsDb: RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}

