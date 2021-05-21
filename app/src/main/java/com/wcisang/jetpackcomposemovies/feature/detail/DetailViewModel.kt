package com.wcisang.jetpackcomposemovies.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcisang.domain.model.Movie
import com.wcisang.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    private val _uiMoviesState: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Success(emptyList()))
    val uiMoviesState: StateFlow<DetailState> = _uiMoviesState

    private val _uiMoviesFavoriteState: MutableStateFlow<DetailFavoriteState> = MutableStateFlow(DetailFavoriteState.IsNotFavorite)
    val uiMoviesFavoriteState: StateFlow<DetailFavoriteState> = _uiMoviesFavoriteState

    private lateinit var movie: Movie

    fun init(movie: Movie) {
        this.movie = movie
        getTrendingMovies()
        getFavoriteStatus(movie)
    }

    private fun getTrendingMovies() {
        viewModelScope.launch {
            _uiMoviesState.value = DetailState.Loading
            _uiMoviesState.value = DetailState.Success(repository.getTrendingMoviesList())
        }
    }

    private fun getFavoriteStatus(movie: Movie) {
        viewModelScope.launch {
            _uiMoviesFavoriteState.value = if (repository.isMovieFavorite(movie)) {
                DetailFavoriteState.IsFavorite
            }else {
                DetailFavoriteState.IsNotFavorite
            }
        }
    }

    fun onFavoriteClick() {
        if (_uiMoviesFavoriteState.value == DetailFavoriteState.IsFavorite) {
            removeFromFavorite()
        }else {
            addMovieToFavorite()
        }
    }

    private fun addMovieToFavorite() {
        viewModelScope.launch {
            repository.addFavoriteMovie(movie)
            _uiMoviesFavoriteState.value = DetailFavoriteState.IsFavorite
        }
    }

    private fun removeFromFavorite() {
        viewModelScope.launch {
            repository.deleteFavoriteMovie(movie)
            _uiMoviesFavoriteState.value = DetailFavoriteState.IsNotFavorite
        }
    }
}