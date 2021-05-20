package com.wcisang.data.di

import android.content.Context
import androidx.room.Room
import com.wcisang.data.local.dao.MoviesDao
import com.wcisang.data.local.database.MovieDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideMovieDataBase(@ApplicationContext appContext: Context): MovieDataBase {
        return Room.databaseBuilder(
            appContext,
            MovieDataBase::class.java, "database-movie"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(dataBase: MovieDataBase): MoviesDao {
        return dataBase.moviesDao()
    }


}