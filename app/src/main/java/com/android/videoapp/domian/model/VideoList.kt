package com.android.videoapp.domian.model

import kotlinx.serialization.Serializable


@Serializable
data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val url: String,
    val channel_name: String,
    val likes: Int,
    val thumbnail_image: String,
)