package com.wcisang.jetpackcomposemovies.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcisang.domain.model.Movie
import com.wcisang.domain.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KSuspendFunction0

private data class MovieCall(
    val apiCall: KSuspendFunction0<List<Movie>>,
    val state: MutableStateFlow<HomeState>
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    private val _uiPopularState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Success(emptyList()))
    val uiPopularState: StateFlow<HomeState> = _uiPopularState

    private val _uiTrendingState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Success(emptyList()))
    val uiTrendingState: StateFlow<HomeState> = _uiTrendingState

    private val _uiUpcomingState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Success(emptyList()))
    val uiUpcomingState: StateFlow<HomeState> = _uiUpcomingState

    private val moviesCallList = listOf(
        MovieCall(
            repository::getPopularMoviesList,
            _uiPopularState
        ),
        MovieCall(
            repository::getTrendingMoviesList,
            _uiTrendingState
        ),
        MovieCall(
            repository::getUpcomingMoviesList,
            _uiUpcomingState
        )
    )

    init {
        setLoadingState()
        moviesCallList.forEach {
            viewModelScope.launch {
                it.state.value = HomeState.Success(it.apiCall.invoke())
            }
        }
    }

    private fun setLoadingState() {
        moviesCallList.forEach {
            it.state.value = HomeState.Loading
        }
    }
}