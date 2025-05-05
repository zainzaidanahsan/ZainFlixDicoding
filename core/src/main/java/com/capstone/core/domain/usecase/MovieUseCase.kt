package com.capstone.core.domain.usecase

import com.capstone.core.data.resource.Resource
import com.capstone.core.domain.model.DetailMovie
import com.capstone.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    suspend fun getMovie(
        token:String,
    ) : Flow<Resource<List<Movie>>>

    suspend fun getDetailPopulerMovie(
        idMovie: Int,
        token: String
    ) : Flow<Resource<DetailMovie>>


    fun getMoviesFavorite(): Flow<List<Movie>>
    fun getMovieFavoriteById(id: Int) : Flow<Boolean>
    suspend fun insertMovie(movie: Movie)
    suspend fun deleteMovie(movie: Movie)
}