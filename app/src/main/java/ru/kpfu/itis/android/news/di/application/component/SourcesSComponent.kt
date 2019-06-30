package ru.kpfu.itis.android.news.di.application.component

import dagger.Subcomponent
import ru.kpfu.itis.android.news.di.application.scope.ScreenScope
import ru.kpfu.itis.android.news.ui.sources.SourcesFragment

@ScreenScope
@Subcomponent
interface SourcesSComponent {
    fun inject(sourcesFragment: SourcesFragment)
}