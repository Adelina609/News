package ru.kpfu.itis.android.news.data.entity

data class Source(
    var id: String,
    var name: String,
    var description: String,
    var url: String?,
    var category: String?,
    var language: String?,
    var country: String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Source

        if (id != other.id) return false
        if (name != other.name) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        return result
    }

}