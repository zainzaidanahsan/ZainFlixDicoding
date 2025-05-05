package com.capstone.core.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.core.data.remote.network.ApiDiscoverService
import com.capstone.core.domain.model.Movie


class MoviePagingSource(
    private val apiService: ApiDiscoverService,
    private val token : String,

) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            Log.i("getRefreshKeySearch ", "anchorPosition = $anchorPosition")

            val anchorPage =state.closestPageToPosition(anchorPosition)
        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        TODO("Not yet implemented")
    }

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> =
//        try {
//            val page  = params.key ?: INITIAL_PAGE_INDEX
//            val movieDiscover =apiService.getMovie(token).results!!
//            Log.i("loadSearch ", "movieDiscover = $movieDiscover")
//            LoadResult.Page(
//                data = movieDiscover,
//                prevKey = if (page == 1) null else page - 1,
//                nextKey = if(movieDiscover.isEmpty()) null else page + 1
//            )
//        } catch (e : Exception){
//            LoadResult.Error(e)
//        }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}