package com.wcisang.data.repository

import com.wcisang.data.local.dao.MoviesDao
import com.wcisang.data.local.mapper.MovieMapper
import com.wcisang.data.remote.service.MoviesService
import com.wcisang.data.remote.util.NetworkConstants
import com.wcisang.domain.model.Movie
import com.wcisang.domain.repository.MoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesService: MoviesService,
    private val moviesDao: MoviesDao
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

    override fun getFavoritesMovieList(): Flow<List<Movie>> {
        return moviesDao.getAllMovies().distinctUntilChanged()
            .map { list -> list.map { MovieMapper.mapMovieEntityToMovie(it) } }
    }

    override suspend fun addFavoriteMovie(movie: Movie) {
        moviesDao.insertAll(MovieMapper.mapMovieToMovieEntity(movie))
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        moviesDao.delete(MovieMapper.mapMovieToMovieEntity(movie))
    }

    override suspend fun isMovieFavorite(movie: Movie): Boolean {
        return moviesDao.isFavorited(movie.id) > 0
    }

}