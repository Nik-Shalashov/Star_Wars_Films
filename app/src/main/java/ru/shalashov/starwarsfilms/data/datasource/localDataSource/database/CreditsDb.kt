package ru.shalashov.starwarsfilms.data.datasource.localDataSource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.dao.CreditsDao
import ru.shalashov.starwarsfilms.data.model.CreditsModel

@Database(entities = [CreditsModel::class], version = 1)
abstract class CreditsDb: RoomDatabase() {
    abstract fun creditsDao(): CreditsDao
}