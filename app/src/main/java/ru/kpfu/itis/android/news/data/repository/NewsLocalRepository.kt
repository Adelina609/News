package ru.kpfu.itis.android.news.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.data.local.model.NewsDB
import ru.kpfu.itis.android.news.data.mapper.mapNewsDBToNews

class NewsLocalRepository(private val newsDao: NewsDao) {
    fun getTopNews(): Single<List<News>> = newsDao.getNewsList()
        .map { it.map { mapNewsDBToNews(it) } }
        .subscribeOn(Schedulers.io())

    fun cacheTopNews(news: NewsDB): Completable = Completable.fromAction {
        if (newsDao.findNewsByAuthorAndTitle(news.author, news.title).isEmpty()) {
            newsDao.insertNewsList(news)
        }
    }.subscribeOn(Schedulers.io())

    fun deleteTopNews(): Completable = Completable.fromAction {
        newsDao.deleteAllNews()
    }.subscribeOn(Schedulers.io())
}
