package com.tomszom.smogapp.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by tsm on 31/03/2018
 */
class NetworkRestAdapter(endpoint: String = GIOS_ENDPOINT) {

    var restAdapter: Retrofit

    init {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(createJsonInterceptor())
        restAdapter = Retrofit.Builder()
                .baseUrl(endpoint)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun createJsonInterceptor(): Interceptor {

        return Interceptor {
            val original = it.request()

            val request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build()

            it.proceed(request)
        }
    }

    fun createGiosAirQualityService(): GiosAirQualityService {
        return restAdapter.create<GiosAirQualityService>(GiosAirQualityService::class.java)
    }
}