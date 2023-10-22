package com.example.natifetest.utils.di

import android.content.Context
import com.example.natifetest.utils.helpers.SharedPrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HelpersModule {
    @Provides
    @Singleton
    fun provideSharedPrefHelper(@ApplicationContext context: Context) = SharedPrefHelper(context)

}