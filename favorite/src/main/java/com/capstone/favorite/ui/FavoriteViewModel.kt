package com.capstone.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.core.domain.model.Movie
import com.capstone.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    val favoriteMovie = movieUseCase.getMoviesFavorite().asLiveData()

    fun deleteMovie (movie: Movie) = viewModelScope.launch {
        movieUseCase.deleteMovie(movie)
    }
}