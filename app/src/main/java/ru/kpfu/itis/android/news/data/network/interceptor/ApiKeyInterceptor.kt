package ru.kpfu.itis.android.news.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiKeyInterceptor private constructor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("apiKey", apiKey).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val apiKey = "0dfd1c0fa3c14d9dbef9c5e3fcafcf63"
        val instance: ApiKeyInterceptor by lazy {
            ApiKeyInterceptor()
        }
    }
}
