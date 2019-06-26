package ru.kpfu.itis.android.news.data.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.kpfu.itis.android.news.data.entity.Source
import ru.kpfu.itis.android.news.data.network.model.ArticleWrapper
import ru.kpfu.itis.android.news.data.network.model.SourceWrapper

interface NewsApi {

    @GET("sources?")
    fun loadSources(): Single<SourceWrapper>

    @GET("top-headlines?sources={source}")
    fun loadArticles(@Path("source") source: Source) : Single<ArticleWrapper>

}