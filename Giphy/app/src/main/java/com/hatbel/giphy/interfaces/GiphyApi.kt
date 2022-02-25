package com.hatbel.giphy.interfaces

import com.hatbel.giphy.model.Giphys
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  GiphyApi {
    @GET("gifs/search")
    suspend fun getGiphys(@Query("q") query: String, @Query("limit") limit: Int, @Query("api_key")  key: String): Response<Giphys>
}