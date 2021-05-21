package com.wcisang.jetpackcomposemovies.feature.detail

import com.wcisang.domain.model.Movie

sealed class DetailState {
    object Loading : DetailState()
    data class Success(val list: List<Movie>) : DetailState()
    data class Failed(val error: String) : DetailState()
}

sealed class DetailFavoriteState {
    object IsFavorite: DetailFavoriteState()
    object IsNotFavorite: DetailFavoriteState()
}