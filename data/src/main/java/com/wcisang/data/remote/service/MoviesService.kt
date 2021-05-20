package com.wcisang.data.remote.service

import com.wcisang.data.remote.response.MoviesResponse
import com.wcisang.data.remote.util.NetworkConstants
import retrofit2.http.GET

interface MoviesService {

    @GET(NetworkConstants.Endpoints.POPULAR_ENDPOINT)
    suspend fun getPopularMoviesList() : MoviesResponse

    @GET(NetworkConstants.Endpoints.TRENDING_ENDPOINT)
    suspend fun getTrendingMoviesList() : MoviesResponse

    @GET(NetworkConstants.Endpoints.UPCOMING_ENDPOINT)
    suspend fun getUpcomingMoviesList() : MoviesResponse
}