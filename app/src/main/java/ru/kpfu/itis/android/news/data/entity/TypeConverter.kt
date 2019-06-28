package ru.kpfu.itis.android.news.data.entity

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import ru.kpfu.itis.android.news.data.network.model.Source

class TypeConverter {

    @TypeConverter
    fun fromString(value: String): Source {
        val listType = object : TypeToken<Source>() {
        }.type
        return Gson().fromJson<Source>(value, listType)
    }

    @TypeConverter
    fun fromSourceBrief(source: Source): String {
        val gson = Gson()
        return gson.toJson(source)
    }
}