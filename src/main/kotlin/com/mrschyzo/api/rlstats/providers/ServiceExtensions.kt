package com.mrschyzo.api.rlstats.providers

import retrofit2.Call
import retrofit2.Response

class ResponseFailedException(
        val response: Response<*>,
        private val additionalMessage: String? = null
) : Exception() {
    val rawResponse: okhttp3.Response
        get() = response.raw()

    override val message: String?
        get() = "Response failed returning HTTP ${rawResponse.code()} ${rawResponse.message()}\nAdditional message: ${additionalMessage ?: "N.A."}"
}

fun <T> Call<T>.getResponseOrException(additionalMessage: String? = null) : T = this.execute().bodyOrException(additionalMessage)
fun <T> Response<T>.bodyOrException(additionalMessage: String? = null) : T = this.body() ?: throw ResponseFailedException(this, additionalMessage)