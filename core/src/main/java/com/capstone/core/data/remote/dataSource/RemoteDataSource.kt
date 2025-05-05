package com.capstone.core.data.remote.dataSource

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.capstone.core.data.remote.network.ApiDiscoverService
import com.capstone.core.data.remote.network.ApiResponse
import com.capstone.core.data.remote.paging.MoviePagingSource
import com.capstone.core.data.remote.response.detail.DetailMovieResponse
import com.capstone.core.data.remote.response.discover.MovieDiscoverResponse
import com.capstone.core.data.resource.ApiSourceResponse
import com.capstone.core.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiDiscoverService) {

    suspend fun getDiscoverMovie(
        apiKey: String
    ) : Flow<ApiSourceResponse<MovieDiscoverResponse>> =
        flow {
            try {
                val response = apiService.getMovie(apiKey)
                emit(ApiSourceResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiSourceResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailPopulerMovie(
        idMovie : Int,
        token: String,
    ): Flow<ApiSourceResponse.Success<DetailMovieResponse>> = flow {
        try {
            val response = apiService.getDetailMovie(idMovie,token)
            emit(ApiSourceResponse.Success(response))
        } catch (e : Exception) {
            e.printStackTrace()
            Log.e("REMOTE_DATA_SOURCE", e.toString())
        }
    }.flowOn(Dispatchers.IO)


//    suspend fun getDiscoverMovie (
//        token: String,
//    ) : Flow<PagingData<Movie>> = Pager(
//        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false),
//        pagingSourceFactory = {
//            Log.i("remote getDiscoverMovie", "masuk")
//            MoviePagingSource(apiService,token) }
//    ).flow
}