package ru.kpfu.itis.android.news.data.mapper

import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.local.model.CachedNews
import ru.kpfu.itis.android.news.data.local.model.NewsDB
import ru.kpfu.itis.android.news.data.network.model.NewsRemote


fun mapNewsRemoteToNews(newsRemote: NewsRemote): News = with(newsRemote) {
    News(0, source, author, title, description, url, urlToImage, publishedAt, content)
}

fun mapNewsDBToNews(newsDB: NewsDB): News = with(newsDB) {
    News(id, source, author, title, description, url, urlToImage, publishedAt, content)
}

fun mapNewsToNewsDB(news: News): NewsDB = with(news) {
    NewsDB(0, source,author, title, description, url, urlToImage, publishedAt, content)
}

fun mapCachedNewsToNewsDB(cachedNews: CachedNews) : NewsDB = with(cachedNews){
    NewsDB(0, source, author, title, description, "", urlToImage, publishedAt, "")
}
