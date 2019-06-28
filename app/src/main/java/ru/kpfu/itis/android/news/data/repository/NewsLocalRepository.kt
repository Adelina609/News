package ru.kpfu.itis.android.news.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.data.mapper.mapNewsDBToNews
import ru.kpfu.itis.android.news.data.mapper.mapNewsToNewsDB

class NewsLocalRepository(private val newsDao: NewsDao) {
    fun getTopNews(): Single<List<News>> = newsDao.getNewsList()
        .map { it.map { mapNewsDBToNews(it) } }
        .subscribeOn(Schedulers.io())

    fun cacheTopNews(newsList: List<News>): Completable = Completable.fromAction {
        newsDao.insertNewsList(newsList.map { mapNewsToNewsDB(it) })
    }.subscribeOn(Schedulers.io())

    fun deleteTopNews(): Completable = Completable.fromAction {
        newsDao.deleteAllNews()
    }.subscribeOn(Schedulers.io())

    fun findNewsById(id: Int): Single<News> = newsDao.findNewsById(id)
        .map { mapNewsDBToNews(it) }
        .subscribeOn(Schedulers.io())
}
