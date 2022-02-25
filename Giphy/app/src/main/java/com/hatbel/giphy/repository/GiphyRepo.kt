package com.hatbel.giphy.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hatbel.giphy.interfaces.GiphyApi
import com.hatbel.giphy.model.GiphyImage
import com.hatbel.giphy.modules.SessionManager
import kotlinx.coroutines.flow.Flow

class GiphyRepo(private val giphyApi: GiphyApi, private val giphyDataSource: GiphyDataSource) {
    fun getGiphysDataSource(): Flow<PagingData<GiphyImage>> = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = true,
            prefetchDistance = 5,
            initialLoadSize = 10
        ),
        pagingSourceFactory = { giphyDataSource }
    ).flow
}