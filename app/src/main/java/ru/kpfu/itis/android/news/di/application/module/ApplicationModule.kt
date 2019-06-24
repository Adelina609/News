package ru.kpfu.itis.android.news.di.application.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.news.di.application.scope.ApplicationScope

@Module
class ApplicationModule(val app: Application) {
    @Provides
    @ApplicationScope
    fun provideApp(): Application = app

    @Provides
    @ApplicationScope
    fun provideContext(): Context = app.applicationContext
}