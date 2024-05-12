package com.android.videoapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.videoapp.domian.repository.VideoListRepository
import com.android.videoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VideoViewModel @Inject constructor(
    private val repository: VideoListRepository,
) : ViewModel() {

    private val _videoListState = MutableStateFlow(VideoListState())
    val videoListState = _videoListState.asStateFlow()

    init {
        getVideo()
    }

    fun onEvent(event : VideoListUiEvent){
        when(event){
            is VideoListUiEvent.Search -> {
                TODO()
            }
            is VideoListUiEvent.PlayVideo -> {
                TODO()
            }
            is VideoListUiEvent.OnHOme -> {
                TODO()
            }
        }
    }

    fun getVideo() {
        Log.d("Movies", "Fetching video list...")
        viewModelScope.launch {
            _videoListState.update {
                it.copy(isLoading = true)
            }
            repository.getVideoList().collectLatest { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _videoListState.update {
                            it.copy(isLoading = false)
                        }
                        Log.e("Movies", "Failed to fetch video list: ${resource.message}")
                        _videoListState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        Log.d("Movies", "Loading video list: ${resource.isLoading}")
                        _videoListState.update {
                            it.copy(isLoading = resource.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        _videoListState.update {
                            it.copy(isLoading = false)
                        }
                        resource.data?.let { videoList ->
                            Log.d("Movies", "Video list fetched successfully: ${videoList.size} videos")
                            _videoListState.update {
                                it.copy(
                                    videoList = videoListState.value.videoList + videoList.shuffled()
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}