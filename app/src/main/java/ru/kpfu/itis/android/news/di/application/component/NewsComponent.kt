package ru.kpfu.itis.android.news.di.application.component

import dagger.Subcomponent
import ru.kpfu.itis.android.news.di.application.scope.NewsScope

@NewsScope
@Subcomponent
interface NewsComponent {
    fun plusNewsSComponent(): NewsSComponent
    fun plusSourcesSComponent(): SourcesSComponent
    fun plusDetailsSComponent(): DetailsSComponent
    fun plusFavoritesSComponent(): FavoritesSComponent
}
