package com.android.videoapp.data.mapper

import com.android.videoapp.data.remoteDTO.VideoDto
import com.android.videoapp.domian.model.Video

fun VideoDto.toVideo(): Video {
    return Video(
        id = id,
        title = title,
        description = description,
        url = url,
        channel_name = channel_name,
        likes = likes,
        thumbnail_image = thumbnail_image,
        profile = profile,
        uploaded = uploaded,
        views = views
    )
}

fun Video.toVideoDto(): VideoDto {
    return VideoDto(
        id = id,
        title = title,
        description = description,
        url = url,
        channel_name = channel_name,
        likes = likes,
        thumbnail_image = thumbnail_image,
        profile = profile,
        uploaded = uploaded,
        views = views
    )
}