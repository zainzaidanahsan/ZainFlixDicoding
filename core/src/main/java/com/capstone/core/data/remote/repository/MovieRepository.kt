package com.capstone.core.data.remote.repository

import com.capstone.core.data.remote.dataSource.LocalDataSource
import com.capstone.core.data.remote.response.detail.DetailMovieResponse
import com.capstone.core.data.resource.NetworkBoundResource
import com.capstone.core.data.resource.Resource
import com.capstone.core.domain.model.DetailMovie
import com.capstone.core.domain.model.Movie
import com.capstone.core.domain.repository.IMovieRepository
import com.capstone.core.utils.DataMapper
import com.capstone.core.data.remote.dataSource.RemoteDataSource
import com.capstone.core.data.remote.response.discover.MovieDiscoverResponse
import com.capstone.core.data.resource.ApiSourceResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {
    override suspend fun moviePopuler(
        apiKey: String
    ): Flow<Resource<List<Movie>>> = object : NetworkBoundResource<List<Movie>, MovieDiscoverResponse>(){
        override fun loadFromNetwork(data: MovieDiscoverResponse): Flow<List<Movie>> =
            DataMapper.mapDiscoverMovieResponseToDomain(data.results)

        override suspend fun createCall(): Flow<ApiSourceResponse<MovieDiscoverResponse>> =
            remoteDataSource.getDiscoverMovie(apiKey)

    }.asFlow()


    override suspend fun getDetailMovie(
        idMovie: Int,
        token: String
    ): Flow<Resource<DetailMovie>> = object : NetworkBoundResource<DetailMovie, DetailMovieResponse>(){
        override fun loadFromNetwork(data: DetailMovieResponse): Flow<DetailMovie> =
            DataMapper.mapDetailMovieResponseToDomain(data)

        override suspend fun createCall(): Flow<ApiSourceResponse<DetailMovieResponse>> =
            remoteDataSource.getDetailPopulerMovie(idMovie,token)

    }.asFlow()

    override fun getMoviesFavorite(): Flow<List<Movie>> =
        localDataSource.getMoviesFavorite().map {
            DataMapper.mapListEntityFavToDomain(it)
        }


    override fun getMovieFavorite(id: Int): Flow<Boolean> =
        localDataSource.getMovieFavoriteById(id)


    override suspend fun insertMovie(movie: Movie) =
        localDataSource.insertMovie(DataMapper.mapMovieDomainToEntity(movie))

    override suspend fun deleteMovie(movie: Movie) =
        localDataSource.deleteMovie(DataMapper.mapMovieDomainToEntity(movie))
}