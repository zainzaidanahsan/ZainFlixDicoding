package com.example.core.utils

object ConstantValue {
//    const val BASE_URL = BuildConfig.API_URL
//    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "3af425520cbfdb9551da71e41b2743f5"
    const val PATH_IMAGE = "https://image.tmdb.org/t/p/w500"
    const val POPULER = "movie/popular"
    const val DETAIL_POPULER = "movie/{movie_id}"
    const val PATH_POPULAR_MOVIE = "${BASE_URL}movie/popular?api_key=$API_KEY"
    const val PATH_DETAIL_MOVIE = "${BASE_URL}movie/{movie_id}?api_key=$API_KEY"

    const val MOVIE_ID = "movie"
}