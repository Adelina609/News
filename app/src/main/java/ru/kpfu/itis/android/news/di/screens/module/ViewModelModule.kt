package ru.kpfu.itis.android.news.di.screens.module

import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.news.data.repository.NewsRepository
import ru.kpfu.itis.android.news.di.screens.scope.NewsScope
import ru.kpfu.itis.android.news.ui.details.DetailsViewModel
import ru.kpfu.itis.android.news.ui.favorites.FavoritesViewModel
import ru.kpfu.itis.android.news.ui.news.NewsViewModel
import ru.kpfu.itis.android.news.ui.sources.SourceViewModel

@Module
class ViewModelModule {
    @Provides
    @NewsScope
    fun provideNewsListViewModel(newsRepository: NewsRepository) = NewsViewModel(newsRepository)

    @Provides
    @NewsScope
    fun provideNewsDetailViewModel(newsRepository: NewsRepository) = DetailsViewModel(newsRepository)

    @Provides
    @NewsScope
    fun provideSourceViewModel(newsRepository: NewsRepository) = SourceViewModel(newsRepository)

    @Provides
    @NewsScope
    fun provideFavoritesViewModel(newsRepository: NewsRepository) = FavoritesViewModel(newsRepository)
}
