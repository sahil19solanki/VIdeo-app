package com.android.videoapp.data.repository

import android.content.Context
import android.util.Log
import coil.network.HttpException
import com.android.videoapp.data.mapper.toVideo
import com.android.videoapp.data.remoteDTO.VideoDto
import com.android.videoapp.domian.model.Video
import com.android.videoapp.domian.repository.VideoListRepository
import com.android.videoapp.util.Resource
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoListRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient,
) : VideoListRepository {
    override suspend fun getVideoList(): Flow<Resource<List<Video>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val videoList =
                    supabaseClient.from("videotable").select().decodeList<VideoDto>()
                emit(
                    Resource.Success(
                        data = videoList.map { video ->
                            video.toVideo()
                        }
                    )
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading video : ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading video : ${e.message}"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading video : ${e.message}"))
                return@flow
            }
        }
    }
}