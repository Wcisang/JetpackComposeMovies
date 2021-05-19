package com.wcisang.data.service

import com.wcisang.data.response.MoviesResponse
import com.wcisang.data.util.NetworkConstants
import retrofit2.http.GET

interface MoviesService {

    @GET(NetworkConstants.Endpoints.POPULAR_ENDPOINT)
    suspend fun getPopularMoviesList() : MoviesResponse

    @GET(NetworkConstants.Endpoints.TRENDING_ENDPOINT)
    suspend fun getTrendingMoviesList() : MoviesResponse

    @GET(NetworkConstants.Endpoints.UPCOMING_ENDPOINT)
    suspend fun getUpcomingMoviesList() : MoviesResponse
}