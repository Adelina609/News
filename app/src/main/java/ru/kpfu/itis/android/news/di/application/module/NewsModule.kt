package ru.kpfu.itis.android.news.di.application.module

import dagger.Module
import dagger.Provides
import ru.kpfu.itis.android.news.data.local.dao.NewsDao
import ru.kpfu.itis.android.news.data.network.NewsApi
import ru.kpfu.itis.android.news.data.repository.NewsLocalRepository
import ru.kpfu.itis.android.news.data.repository.NewsNetworkRepository
import ru.kpfu.itis.android.news.data.repository.NewsRepository
import ru.kpfu.itis.android.news.di.application.scope.ApplicationScope

@Module
class NewsModule {
    @Provides
    @ApplicationScope
    fun provideNewsRepository(
        newsLocalRepository: NewsLocalRepository,
        newsNetworkRepository: NewsNetworkRepository
    ): NewsRepository = NewsRepository(newsNetworkRepository, newsLocalRepository)

    @Provides
    @ApplicationScope
    fun provideNewsNetworkRepository(newsApi: NewsApi): NewsNetworkRepository = NewsNetworkRepository(newsApi)

    @Provides
    @ApplicationScope
    fun provideNewsLocalRepository(newsDao: NewsDao): NewsLocalRepository = NewsLocalRepository(newsDao)
}
