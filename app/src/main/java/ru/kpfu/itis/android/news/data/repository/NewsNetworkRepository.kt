package ru.kpfu.itis.android.news.data.repository

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.entity.Source
import ru.kpfu.itis.android.news.data.mapper.mapNewsRemoteToNews
import ru.kpfu.itis.android.news.data.network.NewsApi

class NewsNetworkRepository constructor(private val newsApi: NewsApi) {
    fun getSources(): Single<List<Source>> = newsApi
        .loadSources()
        .map { it.sources }
        .subscribeOn(Schedulers.io())

    fun getNewsBySource(source:String) : Single<List<News>> = newsApi
        .loadNews(source)
        .map { it.news }
        .map { it.map { mapNewsRemoteToNews(it) } }
        .subscribeOn(Schedulers.io())
}
