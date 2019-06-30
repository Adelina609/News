package ru.kpfu.itis.android.news.di.application.module

import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.news.data.repository.NewsRepository
import ru.kpfu.itis.android.news.di.application.scope.ApplicationScope
import ru.kpfu.itis.android.news.ui.details.DetailsViewModel
import ru.kpfu.itis.android.news.ui.favorites.FavoritesViewModel
import ru.kpfu.itis.android.news.ui.news.NewsViewModel
import ru.kpfu.itis.android.news.ui.sources.SourceViewModel

@Module
class ViewModelModule {
    @Provides
    @ApplicationScope
    fun provideNewsListViewModel(newsRepository: NewsRepository) = NewsViewModel(newsRepository)

    @Provides
    @ApplicationScope
    fun provideNewsDetailViewModel(newsRepository: NewsRepository) = DetailsViewModel(newsRepository)

    @Provides
    @ApplicationScope
    fun provideSourceViewModel(newsRepository: NewsRepository) = SourceViewModel(newsRepository)

    @Provides
    @ApplicationScope
    fun provideFavoritesViewModel(newsRepository: NewsRepository) = FavoritesViewModel(newsRepository)
}


