package ru.kpfu.itis.android.news.di.application.component

import dagger.Subcomponent
import ru.kpfu.itis.android.news.di.application.scope.ScreenScope
import ru.kpfu.itis.android.news.ui.news.NewsFragment

@ScreenScope
@Subcomponent
interface NewsSComponent {
    fun inject(newsFragment: NewsFragment)
}