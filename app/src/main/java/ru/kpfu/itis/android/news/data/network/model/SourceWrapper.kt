package ru.kpfu.itis.android.news.data.network.model

import ru.kpfu.itis.android.news.data.entity.Source

class SourceWrapper(private val status : String,
                    val sources : List<Source>,
                    private val additionalProperties : Map<String, Any>
) {
}