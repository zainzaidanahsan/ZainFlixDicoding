package com.capstone.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.core.adapter.FavoriteAdapter
import com.capstone.core.domain.model.Movie
import com.capstone.favorite.R
import com.capstone.favorite.databinding.FragmentFavoriteBinding
import com.capstone.favorite.di.DaggerFavoriteComponent
import com.capstone.favorite.di.ViewModelFactory
import com.capstone.zainflix.di.FavoriteModule
import dagger.hilt.EntryPoint
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel : FavoriteViewModel by viewModels { factory }
    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private lateinit var  favMovieAdapter : FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFavMovieAdapter()
        observeFavMovie()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDepedencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModule::class.java
                )
            ).build().inject(this)
        super.onCreate(savedInstanceState)
    }

    private fun toDetailFrag(movie:Movie){
        val idBundle = Bundle().apply {
            putInt("id_movie", movie.id)
        }
        findNavController().navigate(com.capstone.zainflix.R.id.action_favoriteFragment_to_detailMoviesFragment, idBundle)
    }

    private fun setFavMovieAdapter() = with(binding.rvFavMovie) {
        favMovieAdapter = FavoriteAdapter{
            toDetailFrag(it)
        }
        layoutManager = LinearLayoutManager(requireContext())
        adapter = favMovieAdapter

    }

    private fun observeFavMovie(){
        viewModel.favoriteMovie.observe(viewLifecycleOwner){movie ->
            favMovieAdapter.submitData(movie as ArrayList<Movie>)

        }
    }

    override fun onDestroyView() {
        binding.rvFavMovie.adapter = null
        super.onDestroyView()
    }
}