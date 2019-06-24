package ru.kpfu.itis.android.news.di.application.component

import android.app.Application
import android.content.Context
import com.squareup.picasso.Picasso
import ru.kpfu.itis.android.news.di.application.scope.ApplicationScope

@ApplicationScope
interface AppComponent {
    fun provideApp(): Application
    fun provideContext(): Context
    fun providePicasso(): Picasso
}