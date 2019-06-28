package ru.kpfu.itis.android.news.data.local.model

import androidx.room.ColumnInfo
import ru.kpfu.itis.android.news.data.network.model.Source

class CachedNews(
    val source: Source?,

    val author: String?,

    val title: String,

    val description: String,

    val urlToImage: String?,

    val publishedAt: String?
    ) {
}