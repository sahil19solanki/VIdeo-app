package com.android.videoapp.domian.repository

import com.android.videoapp.domian.model.Video
import com.android.videoapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface VideoListRepository { suspend fun getVideoList(): Flow<Resource<List<Video>>> }