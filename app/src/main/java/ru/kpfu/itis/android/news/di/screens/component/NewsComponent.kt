package ru.kpfu.itis.android.news.di.screens.component

import ru.kpfu.itis.android.news.di.screens.module.ViewModelModule
import ru.kpfu.itis.android.news.di.screens.scope.NewsScope
import dagger.Component
import ru.kpfu.itis.android.news.di.application.component.AppComponent
import ru.kpfu.itis.android.news.di.screens.module.NewsModule
import ru.kpfu.itis.android.news.ui.MainActivity
import ru.kpfu.itis.android.news.ui.details.DetailsFragment
import ru.kpfu.itis.android.news.ui.favorites.FavoritesFragment
import ru.kpfu.itis.android.news.ui.news.NewsFragment
import ru.kpfu.itis.android.news.ui.sources.SourcesFragment

@NewsScope
@Component(
    dependencies = [AppComponent::class],
    modules = [NewsModule::class, ViewModelModule::class]
)
interface NewsComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(newsFragment: NewsFragment)
    fun inject(newsDetailFragment: DetailsFragment)
    fun inject(favoritesFragment: FavoritesFragment)
    fun inject(sourcesFragment: SourcesFragment)
}
