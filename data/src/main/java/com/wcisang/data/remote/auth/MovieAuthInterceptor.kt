package com.wcisang.data.remote.auth

import com.wcisang.data.remote.util.NetworkConstants
import okhttp3.Interceptor
import okhttp3.Response

class MovieAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("api_key", NetworkConstants.API_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}