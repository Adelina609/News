package ru.kpfu.itis.android.news

import android.app.Application
import com.squareup.picasso.Picasso
import ru.kpfu.itis.android.news.di.application.component.AppComponent
import ru.kpfu.itis.android.news.di.application.component.DaggerAppComponent
import ru.kpfu.itis.android.news.di.application.module.ApplicationModule
import ru.kpfu.itis.android.news.di.application.module.DataDBModule
import ru.kpfu.itis.android.news.di.application.module.DataNetModule
import ru.kpfu.itis.android.news.di.application.module.PicassoModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dataNetModule(DataNetModule())
            .picassoModule(PicassoModule())
            .dataDBModule(DataDBModule())
            .build()
        appComponent?.let {
            Picasso.setSingletonInstance(it.providePicasso())
        }
    }

    companion object {
        private var appComponent: AppComponent? = null

        fun getAppComponents(): AppComponent? = appComponent
    }
}
