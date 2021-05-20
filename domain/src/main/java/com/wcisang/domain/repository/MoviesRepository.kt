package com.wcisang.domain.repository

import com.wcisang.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getPopularMoviesList(): List<Movie>

    suspend fun getTrendingMoviesList(): List<Movie>

    suspend fun getUpcomingMoviesList(): List<Movie>

    fun getFavoritesMovieList(): Flow<List<Movie>>

    suspend fun addFavoriteMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(movie: Movie)
}