package com.example.kadraj.data.di

import com.example.kadraj.data.api.model.Video
import com.example.kadraj.data.repository.LoginRepository
import com.example.kadraj.data.repository.LoginRepositoryImpl
import com.example.kadraj.data.repository.PhotoRepository
import com.example.kadraj.data.repository.PhotoRepositoryImpl
import com.example.kadraj.data.repository.VideoRepository
import com.example.kadraj.data.repository.VideoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl):LoginRepository = loginRepositoryImpl

    @Provides
    @Singleton
    fun providePhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl):PhotoRepository = photoRepositoryImpl

    @Provides
    @Singleton
    fun provideVideoRepository(videoRepositoryImpl: VideoRepositoryImpl):VideoRepository = videoRepositoryImpl
}