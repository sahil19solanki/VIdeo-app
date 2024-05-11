package com.android.videoapp.presentation

import com.android.videoapp.domian.model.Video


data class VideoListState(
    val isLoading: Boolean = false,
    val videoList : List<Video> = emptyList()
)