package com.android.videoapp.di

import android.content.Context
import com.android.videoapp.data.repository.VideoListRepositoryImpl
import com.android.videoapp.domian.repository.VideoListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieListRepository(
        videoListRepositoryImpl: VideoListRepositoryImpl
    ): VideoListRepository
}