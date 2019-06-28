package ru.kpfu.itis.android.news.di.application.component

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Component
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.data.network.NewsApi
import ru.kpfu.itis.android.news.di.application.module.ApplicationModule
import ru.kpfu.itis.android.news.di.application.module.DataDBModule
import ru.kpfu.itis.android.news.di.application.module.DataNetModule
import ru.kpfu.itis.android.news.di.application.module.PicassoModule
import ru.kpfu.itis.android.news.di.application.scope.ApplicationScope

@ApplicationScope
@Component(modules = [ApplicationModule::class, PicassoModule::class, DataNetModule::class, DataDBModule::class])
interface AppComponent {
    fun provideApp(): Application
    fun provideContext(): Context
    fun providePicasso(): Picasso
    fun provideNewsApi(): NewsApi
    fun provideNewsDao(): NewsDao
}