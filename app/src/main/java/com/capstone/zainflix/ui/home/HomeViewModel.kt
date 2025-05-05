package com.capstone.zainflix.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.core.data.resource.Resource
import com.capstone.core.domain.model.Movie
import com.capstone.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel()
{

    private val _moviePopuler = MutableStateFlow<Resource<List<Movie>>>(Resource.Loading())
    val moviePopuler : MutableStateFlow<Resource<List<Movie>>> = _moviePopuler

    fun getDiscoverMovies(token:String){
        viewModelScope.launch {
            movieUseCase.getMovie(token).collectLatest { result ->
                _moviePopuler.value = result
            }
        }
    }
}