package com.wcisang.jetpackcomposemovies.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        viewModelScope.launch {
            _uiMoviesState.value = DetailState.Loading
            _uiMoviesState.value = DetailState.Success(repository.getTrendingMoviesList())
        }
    }
}