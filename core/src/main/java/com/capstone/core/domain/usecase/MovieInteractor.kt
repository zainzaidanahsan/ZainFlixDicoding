package com.capstone.core.domain.usecase

import androidx.paging.PagingData
import com.capstone.core.data.remote.response.MovieItemResponse
import com.capstone.core.data.resource.Resource
import com.capstone.core.domain.model.DetailMovie
import com.capstone.core.domain.model.Movie
import com.capstone.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val repository: IMovieRepository) : MovieUseCase {


    override suspend fun getMovie(token: String): Flow<Resource<List<Movie>>> =
        repository.moviePopuler(token)


//    override suspend fun getPopulerMovie(token: String):
//            Flow<PagingData<MovieItemResponse>> = repository.moviePopuler(token)

    override suspend fun getDetailPopulerMovie(
        idMovie: Int,
        token: String
    ): Flow<Resource<DetailMovie>> =
        repository.getDetailMovie(idMovie, token)

    override fun getMoviesFavorite(): Flow<List<Movie>> = repository.getMoviesFavorite()


    override fun getMovieFavoriteById(id: Int): Flow<Boolean> =
        repository.getMovieFavorite(id)

    override suspend fun insertMovie(movie: Movie) = repository.insertMovie(movie)

    override suspend fun deleteMovie(movie: Movie) = repository.deleteMovie(movie)


}