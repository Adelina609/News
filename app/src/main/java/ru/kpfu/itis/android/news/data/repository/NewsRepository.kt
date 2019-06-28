package ru.kpfu.itis.android.news.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.entity.Source
import ru.kpfu.itis.android.news.data.local.model.CachedNews
import ru.kpfu.itis.android.news.data.local.model.NewsDB
import ru.kpfu.itis.android.news.data.mapper.mapCachedNewsToNewsDB
import ru.kpfu.itis.android.news.data.mapper.mapNewsToNewsDB

class NewsRepository(
    private val newsNetworkRepository: NewsNetworkRepository,
    private val newsLocalRepository: NewsLocalRepository
) {
    fun getSources(): Single<List<Source>> =
        newsNetworkRepository.getSources()

    fun getNewsBySource(sourceId: String): Single<List<News>> =
        newsNetworkRepository.getNewsBySource(sourceId)

    fun getFavoriteNews(): Single<List<News>> =
        newsLocalRepository.getTopNews()

    fun cacheFavoriteNews(cachedNews: CachedNews): Completable =
        newsLocalRepository.cacheTopNews(mapCachedNewsToNewsDB(cachedNews))

    //todo надо?
    fun getNewsById(id: Int): Single<News> = newsLocalRepository.findNewsById(id)

    fun deleteTopNews(): Completable = newsLocalRepository.deleteTopNews()
}

