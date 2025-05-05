package com.capstone.core.domain.repository

import com.capstone.core.data.resource.Resource
import com.capstone.core.domain.model.DetailMovie
import com.capstone.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    suspend fun moviePopuler(
        token:String,
        ) : Flow<Resource<List<Movie>>>

    suspend fun getDetailMovie(
        idMovie :Int,
        token: String
    ) : Flow<Resource<DetailMovie>>

//    fun getPopularMovie() : Flow<Resource<List<Movie>>>

//    fun getDetailMovie(id: Int) : Flow<Resource<DetailMovie>>

    fun getMoviesFavorite(): Flow<List<Movie>>
    fun getMovieFavorite(id: Int) : Flow<Boolean>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)

}