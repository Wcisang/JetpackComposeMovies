package com.wcisang.data.remote.util

object NetworkConstants {

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "69b7b9abff5c26bbe5f01669b74b9643"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    object Endpoints {
        const val POPULAR_ENDPOINT = "movie/popular"
        const val TRENDING_ENDPOINT = "trending/movie/week"
        const val UPCOMING_ENDPOINT = "movie/upcoming"
    }
}