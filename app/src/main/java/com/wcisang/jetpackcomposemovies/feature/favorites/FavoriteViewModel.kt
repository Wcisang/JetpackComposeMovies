package com.wcisang.jetpackcomposemovies.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcisang.domain.model.Movie
import com.wcisang.domain.repository.MoviesRepository
import com.wcisang.jetpackcomposemovies.feature.detail.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _uiMoviesState: MutableStateFlow<FavoriteState> = MutableStateFlow(FavoriteState.Success(emptyList()))
    val uiMoviesState: StateFlow<FavoriteState> = _uiMoviesState

    init {
        getFavoriteList()
    }

    private fun getFavoriteList() {
        _uiMoviesState.value = FavoriteState.Loading
        viewModelScope.launch {
            repository.getFavoritesMovieList().collectLatest {
                _uiMoviesState.value = FavoriteState.Success(it)
            }
        }
    }
}