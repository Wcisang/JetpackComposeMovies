package com.wcisang.jetpackcomposemovies.feature.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.wcisang.designsystem.custom.MoviePoster
import com.wcisang.designsystem.text.MovieTextH6
import com.wcisang.jetpackcomposemovies.R
import com.wcisang.jetpackcomposemovies.feature.home.movies

@ExperimentalFoundationApi
@Composable
fun FavoriteScreenView() {
    Scaffold(topBar = { FavoriteAppBar() }) {
        BodyContent()
    }
}

@Composable
private fun FavoriteAppBar() {
    TopAppBar(
        backgroundColor = Color.Black, modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.toolbar_height))
    ) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxSize()) {
            MovieTextH6(
                text = stringResource(id = R.string.tab_favorites),
                textColor = Color.White
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun BodyContent() {
    LazyVerticalGrid(cells = GridCells.Fixed(3)) {
        items(movies) { movie ->
            MoviePoster(image = movie.poster_path!!)
        }
    }
}

