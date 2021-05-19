package com.wcisang.jetpackcomposemovies.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.wcisang.jetpackcomposemovies.R
import androidx.navigation.compose.*
import com.wcisang.designsystem.custom.MovieLoadingPoster
import com.wcisang.designsystem.custom.MoviePoster
import com.wcisang.designsystem.text.MovieTextH6
import com.wcisang.designsystem.theme.JetpackComposeMoviesTheme
import com.wcisang.domain.model.Movie
import com.wcisang.jetpackcomposemovies.navigation.ScreenMain

@Composable
fun HomeScreenView(navController: NavHostController?, homeViewModel: HomeViewModel = viewModel()) {
    Scaffold(topBar = { MovieAppBar() }) {
        BodyContent(navController, homeViewModel)
    }
}

@Composable
private fun MovieAppBar() {
    TopAppBar(
        backgroundColor = Color.Black, modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.toolbar_height))
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.topbar),
                contentDescription = "",
                modifier = Modifier
                    .size(
                        dimensionResource(id = R.dimen.toolbar_logo_width),
                        dimensionResource(id = R.dimen.toolbar_logo_height)
                    )
            )
        }
    }
}

@Composable
private fun BodyContent(navController: NavHostController?, viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.margin_default))
            .verticalScroll(rememberScrollState())
    )
    {
        val movieClick: (Movie) -> Unit = { movie ->
            navController?.goToMovieDetail(movie)
        }

        MovieListContent(
            textId = R.string.list_label_popular,
            movieState = viewModel.uiPopularState.collectAsState(),
            movieClick = movieClick
        )

        MovieListContent(
            textId = R.string.list_label_trending,
            movieState = viewModel.uiTrendingState.collectAsState(),
            movieClick = movieClick
        )

        MovieListContent(
            textId = R.string.list_label_upcoming,
            movieState = viewModel.uiUpcomingState.collectAsState(),
            movieClick = movieClick
        )
    }
}

@Composable
private fun MovieListContent(
    textId: Int,
    movieState: State<HomeState>,
    movieClick: (Movie) -> Unit
) {
    TextListLabel(textId = textId)
    when (movieState.value) {
        is HomeState.Loading -> LoadingList()
        is HomeState.Success -> MovieList((movieState.value as HomeState.Success).list) {
            movieClick(it)
        }
        is HomeState.Failed -> ListError((movieState.value as HomeState.Failed).error)
    }
}

private fun NavController.goToMovieDetail(movie: Movie) {
    currentBackStackEntry
        ?.arguments?.putParcelable("movie", movie)
    navigate(ScreenMain.Detail.route)
}

@Composable
private fun ListError(error: String) {
    MovieTextH6(text = error, textColor = Color.Red)
}

@Composable
private fun LoadingList() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(3) {
            MovieLoadingPoster(
                modifier = Modifier.padding(horizontal = 6.dp)
            )
        }
    }
}

@Composable
private fun MovieList(list: List<Movie>, movieClick: (Movie) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(list) { movie ->
            MoviePoster(
                image = movie.poster_path!!,
                modifier = Modifier.padding(horizontal = 6.dp)
            ) {
                movieClick(movie)
            }
        }
    }
}

@Composable
private fun TextListLabel(textId: Int) {
    MovieTextH6(
        text = stringResource(id = textId),
        modifier = Modifier.padding(start = 0.dp, top = 20.dp, end = 0.dp, bottom = 7.dp),
        textColor = Color.White
    )
}

@Preview
@Composable
private fun HomeScreenViewPreview() {
    JetpackComposeMoviesTheme {
        HomeScreenView(null)
    }
}

val movies = listOf(
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    ),
    Movie(
        1, false, "",
        "", "", "",
        3.5, "https://image.tmdb.org/t/p/original/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg", "", "",
        false, 3.5, 50
    )
)