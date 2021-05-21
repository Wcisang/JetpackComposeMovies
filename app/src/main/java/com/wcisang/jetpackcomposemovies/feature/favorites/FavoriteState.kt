package com.wcisang.jetpackcomposemovies.feature.favorites

import com.wcisang.domain.model.Movie

sealed class FavoriteState {
    object Loading : FavoriteState()
    data class Success(val list: List<Movie>) : FavoriteState()
}
