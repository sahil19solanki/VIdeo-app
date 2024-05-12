package com.android.videoapp.presentation

sealed interface VideoListUiEvent {
    class OnHOme(val isOnHome: Boolean) : VideoListUiEvent
    data class Search(val query: String) : VideoListUiEvent
    data class PlayVideo(val videoId: Int) : VideoListUiEvent
}