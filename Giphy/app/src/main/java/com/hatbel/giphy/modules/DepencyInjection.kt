package com.hatbel.giphy.modules

import com.hatbel.giphy.repository.GiphyDataSource
import com.hatbel.giphy.repository.GiphyRepo
import com.hatbel.giphy.viewModel.GiphyViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val appModule = module {
    single { SessionManager(androidContext()) }
    viewModel { GiphyViewModel(get(),get()) }
    single { GiphyDataSource(get(),get())}
    single { GiphyRepo(get(), get()) }
    single { RetrofitBuilder().getGipghyApiService() }
}