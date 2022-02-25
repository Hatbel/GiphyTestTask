package com.hatbel.giphy.modules

import com.hatbel.giphy.interfaces.GiphyApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient




class RetrofitBuilder {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getGipghyApiService() = getRetrofit().create(GiphyApi::class.java)
}