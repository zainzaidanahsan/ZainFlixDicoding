package com.capstone.zainflix.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.core.data.resource.Resource
import com.capstone.core.domain.model.DetailMovie
import com.capstone.core.domain.model.Movie
import com.capstone.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailMoviewViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private var _detailMovie = MutableStateFlow<Resource<DetailMovie>?>(null)
    val detailMovie : MutableStateFlow<Resource<DetailMovie>?> = _detailMovie

    fun getDetailPopulerMovie(
        idMovie : Int,
        token :String
    ) = viewModelScope.launch {
        _detailMovie.value = Resource.Loading()
        movieUseCase.getDetailPopulerMovie(idMovie, token).collectLatest {
            _detailMovie.value = it
        }
    }

    fun getFavMovieById(id : Int) : LiveData<Boolean> {
        return movieUseCase.getMovieFavoriteById(id).asLiveData()
    }

    fun insertFavMovie(movie: Movie) = viewModelScope.launch {
        movieUseCase.insertMovie(movie)
    }

    fun deleteFavMovie(movie: Movie) = viewModelScope.launch {
        movieUseCase.deleteMovie(movie)
    }
}