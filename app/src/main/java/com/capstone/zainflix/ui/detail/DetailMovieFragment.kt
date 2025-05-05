package com.capstone.zainflix.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.capstone.core.data.resource.Resource
import com.capstone.core.domain.model.Movie
import com.capstone.core.utils.DataMapper
import com.capstone.zainflix.BuildConfig
import com.capstone.zainflix.R
import com.capstone.zainflix.databinding.FragmentDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


@AndroidEntryPoint
class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {

    private val binding by viewBinding(FragmentDetailMovieBinding::bind)
    private val viewModel : DetailMoviewViewModel by viewModels()
    private var isFavorite by Delegates.notNull<Boolean>()
    private var movie :Movie? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFavMovie()
        setFavorite()
        onBackPressed()
    }

    private fun initFavMovie() {
        val idMovie = arguments?.getInt("id_movie")
        if (idMovie != null  ){
            viewModel.getFavMovieById(idMovie).observe(viewLifecycleOwner){
                isFavorite = it
                setButtonFavorite(it)

            }
            observeDetailPopuler(idMovie)
        }
    }


    private fun setFavorite() = with(binding){
        btnFavorite.setOnClickListener {
            isFavorite = if (!isFavorite){
                movie?.let {movie ->
                    viewModel.insertFavMovie(movie = movie)
                }
                setButtonFavorite(false)
                false
            } else{
                movie?.let {movie ->
                    viewModel.deleteFavMovie(movie = movie)
                    setButtonFavorite(true)
                }
                true
            }
        }
    }

    private fun setButtonFavorite(flag : Boolean) = with(binding){
        if (flag){
            btnFavorite.setIconResource(R.drawable.ic_fav)
        } else {
            btnFavorite.setIconResource(R.drawable.ic_unfav)
        }
    }

    @SuppressLint("CheckResult")
    private fun observeDetailPopuler (idMovie : Int) = with (binding){
        viewModel.getDetailPopulerMovie(
            idMovie = idMovie,
            token = BuildConfig.TOKEN_KEY )
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.detailMovie.collectLatest {
                    it?.let { response ->
                        when(response) {
                            is Resource.Error ->{

                            }
                            is Resource.Loading ->{

                            }
                            is Resource.Success ->{
                                response.data?.let { detailPopulerMovie ->
                                    movie = DataMapper.mapDetailMovieDomaintoMovieDomain(detailMovie = detailPopulerMovie)
                                    Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500${detailPopulerMovie.backdropPath}").into(imgBanner)
                                    tvTitle.text = detailPopulerMovie.title
                                    tvOverview.text = detailPopulerMovie.overview
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onBackPressed() = with(binding){
        topAppBar.setOnClickListener {
            findNavController().navigateUp()
        }
    }



    override fun onStop() {
        super.onStop()
    }
}