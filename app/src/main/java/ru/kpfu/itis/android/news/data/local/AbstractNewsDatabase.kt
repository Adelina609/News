package ru.kpfu.itis.android.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.kpfu.itis.android.news.data.entity.TypeConverter
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.data.local.model.NewsDB

@Database(entities = [NewsDB::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class AbstractNewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
