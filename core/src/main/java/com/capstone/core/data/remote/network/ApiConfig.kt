package com.capstone.core.data.remote.network

import com.capstone.core.BuildConfig.DEBUG
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object{

        fun getDiscoverApiService(): ApiDiscoverService {
            val hostname = "developer.themoviedb.org"
            val certificatePinner = CertificatePinner.Builder()
                .add(hostname, "sha256/hJdIWBzspbVklOxAKu6uW0gpJVgbNbE9tXjL1G8SRPc=")
                .add(hostname, "sha256/qR5muP958z7AZJtxWhkevbLZ8po8upR0EXFA3mVPeog=")
                .add(hostname, "sha256/J2/oqMTsdhFWW/n85tys6b4yDBtb6idZayIEBx7QTxA=")
                .build()
            val level =
                if (DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(level)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiDiscoverService::class.java)
        }
    }
}