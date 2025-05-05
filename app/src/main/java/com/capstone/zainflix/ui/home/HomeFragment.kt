package com.capstone.zainflix.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.core.adapter.MovieAdapter
import com.capstone.core.data.resource.Resource
import com.capstone.zainflix.BuildConfig
import com.capstone.zainflix.R
import com.capstone.zainflix.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private  lateinit var  movieAdapter : MovieAdapter
    private val viewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeMovie()
    }

    private fun setMovieAdapter () = with(binding){
        movieAdapter = MovieAdapter {resultMovieItem ->
            val idMovie = resultMovieItem.id
            if (idMovie != null){
                val bundle = Bundle().apply {
                    putInt("id_movie", idMovie.toInt())
                }
                findNavController().navigate(R.id.action_homeFragment_to_detailMovieFragment, bundle)
            }
        }
        rvMoviesHome.adapter = movieAdapter // baru ditambahkan
        rvMoviesHome.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun observeMovie() = with(binding){
        setMovieAdapter()
        viewModel.getDiscoverMovies(token = BuildConfig.TOKEN_KEY)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.moviePopuler.collectLatest { result ->
                    when (result) {
                        is Resource.Loading -> {
                            // Tampilkan shimmer/loading state
                        }
                        is Resource.Success -> {
                            val movieList = result.data
                            if (!movieList.isNullOrEmpty()) {
                                movieAdapter.submitData(movieList)
                            } else {
                                Log.d("observeMovie", "Data kosong")
                                // Tampilkan empty state UI
                            }
                        }
                        is Resource.Error -> {
                            // Tampilkan UI error
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        binding.rvMoviesHome.adapter = null
        super.onDestroyView()
    }
}