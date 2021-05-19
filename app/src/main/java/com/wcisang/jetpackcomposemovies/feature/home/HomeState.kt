package com.wcisang.jetpackcomposemovies.feature.home

import com.wcisang.domain.model.Movie

sealed class HomeState {
    object Loading : HomeState()
    data class Failed(val error: String): HomeState()
    data class Success(val list: List<Movie>): HomeState()
}
