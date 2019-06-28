package ru.kpfu.itis.android.news.data.entity

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

class TypeConverter {

    @TypeConverter
    fun fromString(value: String): SourceBrief {
        val listType = object : TypeToken<SourceBrief>() {
        }.type
        return Gson().fromJson<SourceBrief>(value, listType)
    }

    @TypeConverter
    fun fromSourceBrief(sourceBrief: SourceBrief): String {
        val gson = Gson()
        return gson.toJson(sourceBrief)
    }
}