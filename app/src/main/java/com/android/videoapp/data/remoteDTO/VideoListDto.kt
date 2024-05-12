package com.android.videoapp.data.remoteDTO

import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    val id: Int,
    val title: String,
    val description: String,
    val url: String,
    val channel_name: String,
    val likes: Int,
    val thumbnail_image: String,
    val profile:String,
    val uploaded:String,
    val views : String
)