package com.capstone.core.adapter

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.capstone.core.databinding.ListFavoriteMovieBinding
import com.capstone.core.databinding.ListMovieBinding
import com.capstone.core.domain.model.Movie


class MovieAdapter(var onClickItem: ((Movie) -> Unit?)? = null) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var diffCallbackUser = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    private var differ = AsyncListDiffer(this, diffCallbackUser)

    fun submitData(valueList: List<Movie>?) {
        differ.submitList(valueList)
    }

    class ViewHolder(var binding: ListMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataMovie = differ.currentList[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context).load("https://image.tmdb.org/t/p/w500${dataMovie.backdropPath}").listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmerPoster.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        shimmerPoster.visibility = View.GONE
                        return false
                    }

                }
            )
                .into(imgPoster)
            title.text = dataMovie.title
            tvDate.text = dataMovie.releaseDate
            tvOriginTitle.text = dataMovie.originalTitle
        }

        holder.itemView.setOnClickListener {
            onClickItem?.invoke(dataMovie)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}