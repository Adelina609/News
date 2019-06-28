package ru.kpfu.itis.android.news.data.network.model

import ru.kpfu.itis.android.news.data.entity.SourceBrief

class NewsRemote (
        val sourceBrief: SourceBrief,

        val author: String?,

        val title: String,

        val description: String,

        val url: String?,

        val urlToImage: String?,

        val publishedAt: String?,

        val content: String?
    ) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as NewsRemote

            if (sourceBrief != other.sourceBrief) return false
            if (title != other.title) return false
            if (url != other.url) return false

            return true
        }

        override fun hashCode(): Int {
            var result = sourceBrief.hashCode() ?: 0
            result = 31 * result + (title.hashCode() ?: 0)
            result = 31 * result + (url?.hashCode() ?: 0)
            return result
        }

    }
