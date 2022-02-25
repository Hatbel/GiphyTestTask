package com.hatbel.giphy.repository

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hatbel.giphy.interfaces.GiphyApi
import com.hatbel.giphy.model.GiphyImage
import com.hatbel.giphy.model.Giphys
import com.hatbel.giphy.modules.SessionManager
import retrofit2.Response

const val GIPHY_KEY = "Zz7XnA0RZzJJetQAQv1e2c7ErivA9F5u"

class GiphyDataSource(private val giphyDao: GiphyApi,
                      private val sessionManager: SessionManager
): PagingSource<Int, GiphyImage>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GiphyImage> {
        val pageNumber = params.key ?: 10
        val response = giphyDao.getGiphys(sessionManager.query, pageNumber, GIPHY_KEY)
        val nextKey =  if(response.body()!!.data.size >= params.loadSize) pageNumber + 10 else null
        val prevKey = if(pageNumber == 0) null else pageNumber-10
        return try {
            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GiphyImage>): Int? {
        val anchPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchPosition) ?: return null
        return page.prevKey?.plus(10) ?: page.nextKey?.minus(1)
    }

}