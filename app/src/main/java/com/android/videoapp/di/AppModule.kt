package com.android.videoapp.di

import android.app.Application
import android.content.Context
import com.android.videoapp.data.repository.VideoListRepositoryImpl
import com.android.videoapp.domian.repository.VideoListRepository
import com.android.videoapp.util.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSupabase(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = Keys.URL,
            supabaseKey = Keys.KEY
        ) {
            install(Postgrest)
        }
    }
}