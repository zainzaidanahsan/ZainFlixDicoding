package com.capstone.core.data.resource

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiSourceResponse.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map {
                    Resource.Success(it)
                })
            }

            is ApiSourceResponse.Error -> {
                emit(Resource.Error(apiResponse.errorMessage))
            }

            ApiSourceResponse.Empty -> {
                Log.d("NetworkBoundResource" , "Data is empty")
            }
        }
    }

    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiSourceResponse<RequestType>>

    fun asFlow(): Flow<Resource<ResultType>> = result
}