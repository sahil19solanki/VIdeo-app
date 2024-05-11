package com.android.videoapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.videoapp.domian.repository.VideoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository : VideoListRepository
) : ViewModel() {
    fun getVideo() {
        viewModelScope.launch {
            repository.getVideoList().collect { resource ->
                when (resource) {
                    is com.android.videoapp.util.Resource.Error ->{

                        Log.e("Movies", "Error: ${resource.message}")
                    }
                    is com.android.videoapp.util.Resource.Loading -> {
                        Log.e("Movies", "Loading: ${resource.isLoading}")
                    }
                    is com.android.videoapp.util.Resource.Success -> {

                        Log.e("Movies", resource.data.toString())
                    }
                }
            }
        }

    }
}