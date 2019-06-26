package ru.kpfu.itis.android.news.data.network.model

import ru.kpfu.itis.android.news.data.entity.Article



class ArticleWrapper (private val status: String?,
    private val totalResults: Int?,
    private val articles: List<Article>?,
    private val additionalProperties: HashMap<String, Any>
) {
}