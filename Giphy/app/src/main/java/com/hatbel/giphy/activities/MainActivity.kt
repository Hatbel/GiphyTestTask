package com.hatbel.giphy.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.hatbel.giphy.R
import com.hatbel.giphy.adapter.GiphyAdapter
import com.hatbel.giphy.adapter.GiphyComparator
import com.hatbel.giphy.databinding.ActivityMainBinding
import com.hatbel.giphy.viewModel.GiphyViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import android.content.Intent

import android.view.View




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val gyphAdapter by lazy { GiphyAdapter(GiphyComparator, this) }

    private val viewModel by viewModel<GiphyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.giphyRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = gyphAdapter
        }
        binding.inputWriteGiphy.doAfterTextChanged {  viewModel.saveQuery(binding.textInputLayout.editText?.text.toString()) }
        binding.giphyRecycler.adapter = gyphAdapter
        lifecycleScope.launch {
            viewModel.giphys.collectLatest { source -> gyphAdapter.submitData(source) }
        }
        playBackgroundSound()
    }
    fun playBackgroundSound() {
        val intent = Intent(this@MainActivity, BackgroundSoundService::class.java)
        startService(intent)
    }
}