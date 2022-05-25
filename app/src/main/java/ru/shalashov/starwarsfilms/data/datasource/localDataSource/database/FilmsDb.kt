package ru.shalashov.starwarsfilms.data.datasource.localDataSource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao.FilmsDao
import ru.shalashov.starwarsfilms.data.model.FilmsModel

@Database(entities = [FilmsModel::class], version = 1)
abstract class FilmsDb: RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}

