package com.wcisang.jetpackcomposemovies.feature.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.wcisang.designsystem.custom.MoviePoster
import com.wcisang.designsystem.text.MovieTextH6
import com.wcisang.domain.model.Movie
import com.wcisang.jetpackcomposemovies.R
import com.wcisang.jetpackcomposemovies.navigation.ScreenMain

@ExperimentalFoundationApi
@Composable
fun FavoriteScreenView(mainNavController: NavHostController?, viewModel: FavoriteViewModel) {
    Scaffold(topBar = { FavoriteAppBar() }) {
        BodyContent(viewModel, mainNavController)
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
private fun BodyContent(viewModel: FavoriteViewModel, mainNavController: NavHostController?) {
    val state = viewModel.uiMoviesState.collectAsState()
    when (state.value) {
        is FavoriteState.Loading -> LoadingList()
        is FavoriteState.Success -> MoviesList((state.value as FavoriteState.Success).list) {
            mainNavController?.goToMovieDetail(it)
        }
    }
}

private fun NavController.goToMovieDetail(movie: Movie) {
    currentBackStackEntry
        ?.arguments?.putParcelable("movie", movie)
    navigate(ScreenMain.Detail.route)
}

@ExperimentalFoundationApi
@Composable
fun MoviesList(list: List<Movie>, onClick: (Movie) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        Modifier.padding(vertical = 16.dp)
    ) {
        items(list) { movie ->
            MoviePoster(
                image = movie.poster_path!!,
                modifier = Modifier
                    .padding(6.dp)
            ){
                onClick(movie)
            }
        }
    }
}

@Composable
fun LoadingList() {

}

