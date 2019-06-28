package ru.kpfu.itis.android.news.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.kpfu.itis.android.news.data.network.model.Source

@Entity(tableName = "news_data")
data class NewsDB(
    @ColumnInfo(index = true)
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "source")
    val source: Source?,

    val author: String?,

    val title: String,

    val description: String,

    val url: String?,

    @ColumnInfo(name = "url_to_image")
    val urlToImage: String?,

    @ColumnInfo(name = "published_at")
    val publishedAt: String?,

    val content: String?
)
