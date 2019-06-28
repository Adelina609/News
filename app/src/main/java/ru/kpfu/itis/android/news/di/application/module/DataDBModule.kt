package ru.kpfu.itis.android.news.di.application.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.news.data.local.AbstractNewsDatabase
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.di.application.scope.ApplicationScope

@Module
class DataDBModule {
    companion object {
        private const val DATABASE_NAME = "news_db.db"
    }

    @Provides
    @ApplicationScope
    fun provideNewsDatabase(app: Application): AbstractNewsDatabase = Room.databaseBuilder(
        app,
        AbstractNewsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @ApplicationScope
    fun provideNewsDao(database: AbstractNewsDatabase): NewsDao = database.newsDao()
}
