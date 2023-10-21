package com.example.natifetest.utils.di

import com.example.natifetest.data.repository.MainRepository
import com.example.natifetest.data.repository.impl.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}