package com.wcisang.domain.repository

import com.wcisang.domain.model.Movie

interface MoviesRepository {

    suspend fun getPopularMoviesList(): List<Movie>

    suspend fun getTrendingMoviesList(): List<Movie>

    suspend fun getUpcomingMoviesList(): List<Movie>
}