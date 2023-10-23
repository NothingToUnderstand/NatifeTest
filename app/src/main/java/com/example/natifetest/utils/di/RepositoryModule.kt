package com.example.natifetest.utils.di

import com.example.natifetest.data.repository.LocalRepository
import com.example.natifetest.data.repository.RemoteRepository
import com.example.natifetest.data.repository.impl.LocalRepositoryImpl
import com.example.natifetest.data.repository.impl.RemoteRepositoryImpl
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
    fun bindRemoteRepository(
        remoteRepositoryImpl: RemoteRepositoryImpl
    ): RemoteRepository

    @Binds
    @Singleton
    fun bindLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository

}