package com.mrschyzo.api.rlstats.utils.data

import com.mrschyzo.api.rlstats.utils.CallFunnel
import com.mrschyzo.api.rlstats.utils.ThrottledCallFunnel
import com.mrschyzo.api.rlstats.services.RLService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

data class RetrofitServiceProperties(
        val requests: Long,
        val interval: TimeSpan,
        val apiKey: String,
        val apiRootUrl: String
)

fun RetrofitServiceProperties.generateCallTunnel() : CallFunnel = ThrottledCallFunnel(interval, requests)

fun RetrofitServiceProperties.generateClient() : OkHttpClient {
    val tunnel = generateCallTunnel()

    return OkHttpClient().newBuilder().addInterceptor { chain ->
        tunnel.run {
            val req = chain.request()
            val newReq = req?.newBuilder()?.addHeader("Authorization", apiKey)?.build()

            chain.proceed(newReq!!)
        }
    }.build()
}

fun RetrofitServiceProperties.generateRLService() : RLService =
        Retrofit.Builder()
                .baseUrl(apiRootUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(generateClient())
                .build()
                .create(RLService::class.java)