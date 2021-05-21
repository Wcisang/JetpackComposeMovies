package com.wcisang.jetpackcomposemovies.feature.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.navigate
import com.google.accompanist.coil.rememberCoilPainter
import com.wcisang.designsystem.button.PrimaryButton
import com.wcisang.designsystem.button.SecondaryButton
import com.wcisang.designsystem.custom.IconToolbarArrowBack
import com.wcisang.designsystem.custom.MovieLoadingPoster
import com.wcisang.designsystem.custom.MoviePoster
import com.wcisang.designsystem.text.MovieTextBody1
import com.wcisang.designsystem.text.MovieTextH5
import com.wcisang.designsystem.text.MovieTextH6
import com.wcisang.designsystem.theme.BlackTransparent
import com.wcisang.designsystem.theme.LightBlackBackground
import com.wcisang.designsystem.theme.TextColorSubtitle
import com.wcisang.domain.model.Movie
import com.wcisang.jetpackcomposemovies.R
import com.wcisang.jetpackcomposemovies.navigation.ScreenMain

@ExperimentalFoundationApi
@Composable
fun DetailScreenView(
    navController: NavController?, viewModel: DetailViewModel = viewModel(), movie: Movie
) {
    viewModel.init(movie)
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            MovieBackground(image = movie.poster_path!!)
            Surface(
                color = BlackTransparent,
                modifier = Modifier.fillMaxSize()
            ) {
                DetailContent(movie = movie, viewModel, navController)
            }
            IconToolbarArrowBack(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                navController?.popBackStack()
            }
        }
    }
}

@Composable
private fun MovieBackground(image: String) {
    Image(
        painter = rememberCoilPainter(
            request = image
        ),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@ExperimentalFoundationApi
@Composable
private fun DetailContent(movie: Movie, viewModel: DetailViewModel, navController: NavController?) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 60.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(elevation = 8.dp) {
            MoviePoster(
                image = movie.poster_path!!,
                modifier = Modifier.padding(0.dp)
            )
        }
        TextMovieContent(movie = movie)
        ButtonContent(viewModel)
        MovieTabs(viewModel, navController)
    }
}

@Composable
private fun ColumnScope.TextMovieContent(movie: Movie) {
    MovieTextH5(
        text = movie.title,
        textColor = Color.White,
        modifier = Modifier.padding(20.dp)
    )
    MovieTextBody1(
        text = movie.overview,
        modifier = Modifier
            .padding(16.dp)
            .align(Alignment.Start),
        textAlign = TextAlign.Start,
        textColor = TextColorSubtitle
    )
}

@Composable
private fun ButtonContent(viewModel: DetailViewModel) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PrimaryButton(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp),
            text = stringResource(id = R.string.detail_watch_button),
            icon = Icons.Default.PlayArrow
        ) {
            //TODO Clique do botão assista
        }

        FavoriteButton(viewModel)
    }
}

@Composable
private fun RowScope.FavoriteButton(viewModel: DetailViewModel) {
    val state = viewModel.uiMoviesFavoriteState.collectAsState()
    SecondaryButton(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 10.dp),
        text = if (state.value is DetailFavoriteState.IsFavorite) stringResource(id = R.string.detail_save_button_saved)
        else stringResource(id = R.string.detail_save_button),
        icon = if (state.value is DetailFavoriteState.IsFavorite) Icons.Default.Check
        else Icons.Default.Star
    ) {
        viewModel.onFavoriteClick()
    }
}

sealed class Tabs(val index: Int, val titleId: Int) {
    class Tab1 : Tabs(0, R.string.detail_more_tab)
    class Tab2 : Tabs(1, R.string.detail_movie_tab)
}

@ExperimentalFoundationApi
@Composable
fun MovieTabs(viewModel: DetailViewModel, navController: NavController?) {
    val tab = remember { mutableStateOf<Tabs>(Tabs.Tab1()) }
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tab.value.index,
            backgroundColor = Color.Black,
            contentColor = Color.White,
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.margin_default)
            )
        ) {
            Tab(
                selected = tab.value.index == Tabs.Tab1().index,
                onClick = { tab.value = Tabs.Tab1() }
            ) {
                MovieTextBody1(
                    text = stringResource(id = Tabs.Tab1().titleId),
                    modifier = Modifier.padding(10.dp)
                )
            }
            Tab(
                selected = tab.value.index == Tabs.Tab2().index,
                onClick = { tab.value = Tabs.Tab2() }
            ) {
                MovieTextBody1(
                    text = stringResource(id = Tabs.Tab2().titleId),
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Surface(color = LightBlackBackground, modifier = Modifier.fillMaxSize()) {
            when (tab.value) {
                is Tabs.Tab1 -> MoviesContentList(viewModel.uiMoviesState.collectAsState(), navController)
                is Tabs.Tab2 -> MovieTechicalDetail()
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun MoviesContentList(movieState: State<DetailState>, navController: NavController?) {
    when (movieState.value) {
        is DetailState.Loading -> LoadingList()
        is DetailState.Success -> MoviesList((movieState.value as DetailState.Success).list){
            navController?.goToMovieDetail(it)
        }
        is DetailState.Failed -> ListError((movieState.value as DetailState.Failed).error)
    }
}

private fun NavController.goToMovieDetail(movie: Movie) {
    currentBackStackEntry
        ?.arguments?.putParcelable("movie", movie)
    navigate(ScreenMain.Detail.route)
}

@ExperimentalFoundationApi
@Composable
private fun MoviesList(movies: List<Movie>, onClick: (Movie) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxHeight()
    ) {
        items(movies) { movie ->
            MoviePoster(image = movie.poster_path!!, modifier = Modifier.padding(8.dp)) {
                onClick(movie)
            }
        }
    }
}

@Composable
private fun LoadingList() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(3) {
            MovieLoadingPoster(
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
private fun ListError(error: String) {
    MovieTextH6(text = error, textColor = Color.Red)
}

@Composable
fun MovieTechicalDetail() {
    Text(text = "Detalhes do filme")
}
