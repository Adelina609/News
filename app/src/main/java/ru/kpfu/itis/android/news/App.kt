package ru.kpfu.itis.android.news

import android.app.Application
import com.squareup.picasso.Picasso
import ru.kpfu.itis.android.news.di.application.component.*
import ru.kpfu.itis.android.news.di.application.module.ApplicationModule
import ru.kpfu.itis.android.news.di.application.module.DataDBModule
import ru.kpfu.itis.android.news.di.application.module.DataNetModule
import ru.kpfu.itis.android.news.di.application.module.PicassoModule

class App : Application() {

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .dataDBModule(DataDBModule())
            .dataNetModule(DataNetModule())
            .picassoModule(PicassoModule())
            .build()

        super.onCreate()
        appComponent?.let {
            Picasso.setSingletonInstance(it.providePicasso())
        }
    }

    fun plusNewsComponent(): NewsComponent {
        if (newsComponent == null) {
            newsComponent = appComponent?.plusNewsSComponent()
        }
        return newsComponent as NewsComponent
    }

    fun plusDetailsSComponent(): DetailsSComponent {
        if (detailsSComponent == null) {
            detailsSComponent = plusNewsComponent().plusDetailsSComponent()
        }
        return detailsSComponent as DetailsSComponent
    }

    fun plusFavoritesSComponent(): FavoritesSComponent {
        if (favoritesSComponent == null) {
            favoritesSComponent = plusNewsComponent().plusFavoritesSComponent()
        }
        return favoritesSComponent as FavoritesSComponent
    }

    fun plusNewsSComponent(): NewsSComponent {
        if (newsSComponent == null) {
            newsSComponent = plusNewsComponent().plusNewsSComponent()
        }
        return newsSComponent as NewsSComponent
    }

    fun plusSourcesSComponent(): SourcesSComponent {
        if (sourcesSComponent == null) {
            sourcesSComponent = plusNewsComponent().plusSourcesSComponent()
        }
        return sourcesSComponent as SourcesSComponent
    }

    fun clearNewsSComponent() {
        newsSComponent = null
    }

    fun clearDetailsSComponent() {
        detailsSComponent = null
    }

    fun clearFavoritesSComponent() {
        favoritesSComponent = null
    }

    fun clearSourcesSComponent() {
        sourcesSComponent = null
    }

    companion object {
        private var appComponent: AppComponent? = null
        private var detailsSComponent: DetailsSComponent? = null
        private var favoritesSComponent: FavoritesSComponent? = null
        private var newsComponent: NewsComponent? = null
        private var newsSComponent: NewsSComponent? = null
        private var sourcesSComponent: SourcesSComponent? = null
        fun getAppComponents(): AppComponent? = appComponent
    }
}
