package com.capstone.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.capstone.core.data.local.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDAO {
    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT COUNT(*) FROM movie WHERE id = :id")
    fun getMoviesById(id: Int): Flow<Boolean>

    @Insert
    suspend fun insertMovieFavorite(movie: MovieEntity)

    @Delete
    suspend fun deleteMovieFavorite(movie: MovieEntity)
}