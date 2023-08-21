package com.example.kadraj.data.di

import com.example.kadraj.data.repository.LoginRepository
import com.example.kadraj.data.repository.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl):LoginRepository = loginRepositoryImpl
}