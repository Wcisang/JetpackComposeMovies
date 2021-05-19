package com.wcisang.data.repository

import com.wcisang.data.service.MoviesService
import com.wcisang.data.util.NetworkConstants
import com.wcisang.domain.model.Movie
import com.wcisang.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService
) : MoviesRepository {

    override suspend fun getPopularMoviesList(): List<Movie> {
        return moviesService.getPopularMoviesList().results.map { movie ->
            delay(300)
            movie.copy(poster_path = NetworkConstants.IMAGE_BASE_URL + movie.poster_path)
        }
    }

    override suspend fun getTrendingMoviesList(): List<Movie> {
        return moviesService.getTrendingMoviesList().results.map { movie ->
            movie.copy(poster_path = NetworkConstants.IMAGE_BASE_URL + movie.poster_path)
        }
    }

    override suspend fun getUpcomingMoviesList(): List<Movie> {
        return moviesService.getUpcomingMoviesList().results.map { movie ->
            delay(100)
            movie.copy(poster_path = NetworkConstants.IMAGE_BASE_URL + movie.poster_path)
        }
    }

}