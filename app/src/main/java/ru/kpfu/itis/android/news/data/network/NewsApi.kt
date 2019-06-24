package ru.kpfu.itis.android.news.data.network

import io.reactivex.Single
import retrofit2.http.GET

interface NewsApi {
    //TODO
    @GET("/top-headlines?sources={}")
    fun loadTopHeadlines(): Single<NewsApiResponse>
}