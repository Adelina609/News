package ru.kpfu.itis.android.news.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kpfu.itis.android.news.data.entity.Source
import ru.kpfu.itis.android.news.data.network.model.NewsWrapper
import ru.kpfu.itis.android.news.data.network.model.SourceWrapper
import java.sql.Struct

interface NewsApi {

    @GET("sources?")
    fun loadSources(): Single<SourceWrapper>

    @GET("top-headlines")
    fun loadNews(@Query("sources") source: String) : Single<NewsWrapper>

}