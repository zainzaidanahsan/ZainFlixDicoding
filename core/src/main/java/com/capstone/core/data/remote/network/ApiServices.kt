package com.capstone.core.data.remote.network

import com.capstone.core.data.remote.response.detail.DetailMovieResponse
import com.capstone.core.data.remote.response.discover.MovieDiscoverResponse
import com.capstone.core.utils.EndPoint.DETAIL_MOVIE
import com.capstone.core.utils.EndPoint.MOVIE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiDiscoverService {

    @GET(MOVIE)
    suspend fun getMovieList(
        @Header("Authorization") token : String,
        @Query("include_adult") adultStatus : Boolean,
        @Query("include_video") videoStatus : Boolean,
        @Query("language") language : String,
        @Query("page") page : Int,
        @Query("sort_by") sortBy : String
    ) : MovieDiscoverResponse

    @GET(MOVIE)
    suspend fun getMovie(
        @Query("api_key") apiKey : String
    ) : MovieDiscoverResponse

    @GET(DETAIL_MOVIE)
    suspend fun getDetailMovie(
        @Path("movie_id") idMovie : Int,
        @Query("api_key") apiKey : String
    ) : DetailMovieResponse

}

interface ApiServicesDetail {


}