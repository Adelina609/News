package ru.kpfu.itis.android.news.di.screens.module

import ru.kpfu.itis.android.news.di.screens.scope.NewsScope
import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.data.network.NewsApi
import ru.kpfu.itis.android.news.data.repository.NewsLocalRepository
import ru.kpfu.itis.android.news.data.repository.NewsNetworkRepository
import ru.kpfu.itis.android.news.data.repository.NewsRepository

@Module
class NewsModule {
    @Provides
    @NewsScope
    fun provideNewsRepository(
        newsLocalRepository: NewsLocalRepository,
        newsNetworkRepository: NewsNetworkRepository
    ): NewsRepository = NewsRepository(newsNetworkRepository, newsLocalRepository)

    @Provides
    @NewsScope
    fun provideNewsNetworkRepository(newsApi: NewsApi): NewsNetworkRepository = NewsNetworkRepository(newsApi)

    @Provides
    @NewsScope
    fun provideNewsLocalRepository(newsDao: NewsDao): NewsLocalRepository = NewsLocalRepository(newsDao)

//    @Provides
//    @NewsScope
//    fun provideNewsInteractor(newsRepository: NewsRepository): TopNewsInteractor = TopNewsInteractor(newsRepository)
}
