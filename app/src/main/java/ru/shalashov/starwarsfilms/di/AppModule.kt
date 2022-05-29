package ru.shalashov.starwarsfilms.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.CreditsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.DetailsDb
import ru.shalashov.starwarsfilms.data.datasource.localDataSource.database.FilmsDb
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getDetailsDb(context: Context): DetailsDb  =
        Room.databaseBuilder(context.applicationContext, DetailsDb::class.java, "details_db").build()

    @Provides
    @Singleton
    fun getFilmsDb(context: Context): FilmsDb =
        Room.databaseBuilder(context.applicationContext, FilmsDb::class.java, "films_db").build()

    @Provides
    @Singleton
    fun getCreditsDb(context: Context): CreditsDb =
        Room.databaseBuilder(context.applicationContext, CreditsDb::class.java, "credits_db").build()
}