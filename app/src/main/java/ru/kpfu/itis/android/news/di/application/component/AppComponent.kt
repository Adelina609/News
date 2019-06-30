package ru.kpfu.itis.android.news.di.application.component

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Component
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.data.network.NewsApi
import ru.kpfu.itis.android.news.di.application.module.*
import ru.kpfu.itis.android.news.di.application.scope.ApplicationScope

@ApplicationScope
@Component(
    modules = [ApplicationModule::class, PicassoModule::class, DataNetModule::class,
        DataDBModule::class, NewsModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun plusNewsSComponent(): NewsComponent
    fun provideApp(): Application
    fun provideContext(): Context
    fun providePicasso(): Picasso
    fun provideNewsApi(): NewsApi
    fun provideNewsDao(): NewsDao
}