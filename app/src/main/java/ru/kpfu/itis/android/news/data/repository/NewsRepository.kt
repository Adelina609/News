package ru.kpfu.itis.android.news.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.entity.Source

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

    fun cacheFavoriteNews(newsList: List<News>): Completable =
        newsLocalRepository.cacheTopNews(newsList)

    //todo надо?
    fun getNewsById(id: Int): Single<News> = newsLocalRepository.findNewsById(id)

    fun deleteTopNews(): Completable = newsLocalRepository.deleteTopNews()
}

