package ru.kpfu.itis.android.news.di.application.component

import dagger.Subcomponent
import ru.kpfu.itis.android.news.di.application.scope.ScreenScope
import ru.kpfu.itis.android.news.ui.details.DetailsFragment

@ScreenScope
@Subcomponent
interface DetailsSComponent {
    fun inject(newsDetailFragment: DetailsFragment)
}