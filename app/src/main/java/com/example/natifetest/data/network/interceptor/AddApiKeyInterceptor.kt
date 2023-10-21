package com.example.natifetest.data.network.interceptor

import com.example.natifetest.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class AddApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()

        val url = req.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        return chain.proceed(
            req.newBuilder()
                .url(url)
                .build()
        )
    }
}