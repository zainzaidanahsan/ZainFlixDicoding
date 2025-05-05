package com.capstone.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.capstone.core.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun movieDAO() : FavoriteMovieDAO

}