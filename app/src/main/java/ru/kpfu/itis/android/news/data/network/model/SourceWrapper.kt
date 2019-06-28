package ru.kpfu.itis.android.news.data.network.model

import ru.kpfu.itis.android.news.data.entity.Source

class SourceWrapper(val status : String,
                    val sources : List<Source>,
                    val additionalProperties : Map<String, Any>
) {
}