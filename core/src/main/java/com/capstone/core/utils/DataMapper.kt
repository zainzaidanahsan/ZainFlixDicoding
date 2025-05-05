package com.capstone.core.utils

import com.capstone.core.data.local.entities.MovieEntity
import com.capstone.core.data.remote.response.MovieItemResponse
import com.capstone.core.data.remote.response.detail.DetailMovieResponse
import com.capstone.core.domain.model.DetailMovie
import com.capstone.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    fun mapDiscoverMovieResponseToDomain(movieItemResponse: List<MovieItemResponse>)
    : Flow<List<Movie>> {
        val movieList = ArrayList<Movie>()
        movieItemResponse.map {
            val movie = Movie(
                it.id!!,
                it.backdropPath.toString(),
                it.title.toString(),
                it.overview.toString(),
                it.originalTitle.toString(),
                it.releaseDate.toString()
            )
            movieList.add(movie)
        }
        return flowOf(movieList)
    }

//    = flowOf(
//        Movie (
//            id = movieItemResponse.!!,
//            backdropPath = movieItemResponse.backdropPath.toString(),
//            title = movieItemResponse.title.toString(),
//            overview = movieItemResponse.overview.toString(),
//            originalTitle = movieItemResponse.originalTitle.toString(),
//            releaseDate = movieItemResponse.releaseDate.toString()
//        )
//    )

    fun mapDetailMovieResponseToDomain(movieDetailResponse: DetailMovieResponse)
            : Flow<DetailMovie> = flowOf(
        DetailMovie(
            id = movieDetailResponse.id!!,
            backdropPath = movieDetailResponse.backdropPath!!,
            originalTitle = movieDetailResponse.originalTitle!!,
            title = movieDetailResponse.title!!,
            popularity = movieDetailResponse.popularity!!.toString(),
            overview = movieDetailResponse.overview!!,
            posterPath = movieDetailResponse.posterPath!!,
            releaseDate = movieDetailResponse.releaseDate!!,
            voteAverage = movieDetailResponse.voteAverage!!.toString(),
            homepage = movieDetailResponse.homepage!!
        )
    )

    fun mapDetailMovieDomaintoMovieDomain(detailMovie: DetailMovie) : Movie =
        Movie(
            id = detailMovie.id,
            backdropPath = detailMovie.posterPath,
            title = detailMovie.title,
            originalTitle = detailMovie.originalTitle,
            overview = detailMovie.overview,
            releaseDate = detailMovie.releaseDate
        )

    fun mapMovieDomainToEntity(movie: Movie)
            : MovieEntity =
        MovieEntity(
            id = movie.id,
            posterPath = movie.backdropPath,
            title = movie.title,
            originalTitle = movie.originalTitle,
            overview = movie.overview,
            releaseDate = movie.releaseDate
        )

    fun mapListEntityFavToDomain(movie: List<MovieEntity>) : List<Movie> {
        val listFavMovie = ArrayList<Movie>()
        movie.map {
            val movie = Movie(
                id = it.id,
                backdropPath = it.posterPath,
                title = it.title,
                originalTitle = it.originalTitle,
                overview = it.overview,
                releaseDate = it.releaseDate
            )
            listFavMovie.add(movie)
        }
        return listFavMovie
    }
}