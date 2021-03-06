package com.puzzlebench.cmk.data.service

import com.puzzlebench.cmk.data.BuildConfig
import com.puzzlebench.cmk.data.service.api.MarvelApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MarvelResquestGenerator {
    private val PRIVATE_API_KEY_ARG = "hash"
    private val PUBLIC_API_KEY_ARG = "apikey"
    private val TS = "ts"
    private val TS_VALUE = "1"
    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val defaultRequest = chain.request()

        val defaultHttpUrl = defaultRequest.url()
        val httpUrl = defaultHttpUrl.newBuilder()
                .addQueryParameter(PUBLIC_API_KEY_ARG, BuildConfig.PUBLIC_API_KEY_VALUE)
                .addQueryParameter(PRIVATE_API_KEY_ARG, BuildConfig.PRIVATE_API_KEY_VALUE)
                .addQueryParameter(TS, TS_VALUE)
                .build()

        val requestBuilder = defaultRequest.newBuilder().url(httpUrl)

        chain.proceed(requestBuilder.build())
    }

    private val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.MARVEL_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())

    private fun makeMarvelService(okHttpClient: OkHttpClient): MarvelApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.MARVEL_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(MarvelApi::class.java)
    }

    fun makeMarvelService(): MarvelApi {
        val okHttpClient = httpClient.build()
        return makeMarvelService(okHttpClient)
    }
}