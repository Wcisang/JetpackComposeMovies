package com.wcisang.data.local.dao

import androidx.room.*
import com.wcisang.data.local.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg gists: MovieEntity)

    @Delete
    suspend fun delete(gist: MovieEntity)
}