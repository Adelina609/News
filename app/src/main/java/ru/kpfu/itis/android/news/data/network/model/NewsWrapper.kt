package ru.kpfu.itis.android.news.data.network.model

import com.google.gson.annotations.SerializedName

data class NewsWrapper(
    val status: String?,
    val totalResults: Int?,
    @SerializedName("articles") val news: List<NewsRemote>
    //val additionalProperties: HashMap<String, Any>
)