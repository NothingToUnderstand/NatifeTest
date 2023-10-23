package com.example.natifetest.utils.di

import com.example.natifetest.BuildConfig
import com.example.natifetest.data.model.Gif
import com.example.natifetest.data.network.adapter.GifAdapter
import com.example.natifetest.data.network.interceptor.AddApiKeyInterceptor
import com.example.natifetest.data.repository.ProjectApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideAddApiKeyInterceptor() = AddApiKeyInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(addApiKeyInterceptor: AddApiKeyInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(addApiKeyInterceptor)
            .addNetworkInterceptor(
                if (BuildConfig.DEBUG) HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                ) else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            ).build()

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): ProjectApi {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    Gson().newBuilder()
                        .registerTypeAdapter(Gif::class.java, GifAdapter())
                        .create()
                )
            )
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
            .create(ProjectApi::class.java)
    }
}