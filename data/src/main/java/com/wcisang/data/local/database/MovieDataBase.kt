package com.wcisang.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wcisang.data.local.dao.MoviesDao
import com.wcisang.data.local.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase(){

    abstract fun moviesDao() : MoviesDao
}
