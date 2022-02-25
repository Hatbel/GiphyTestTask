package com.hatbel.giphy.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.hatbel.giphy.model.GiphyImage
import com.hatbel.giphy.modules.SessionManager
import com.hatbel.giphy.repository.GiphyDataSource
import com.hatbel.giphy.repository.GiphyRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class GiphyViewModel(
    private val sessionManager: SessionManager,
    private val giphyRepo : GiphyRepo
) : ViewModel() {
    var giphys: Flow<PagingData<GiphyImage>>
    init{
        sessionManager.query = "anime"
        giphys = giphyRepo.getGiphysDataSource()
    }



    fun saveQuery(query: String) {
        sessionManager.query = query
        giphys = giphyRepo.getGiphysDataSource()
    }
}