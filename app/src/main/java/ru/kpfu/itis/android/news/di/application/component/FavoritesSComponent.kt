package ru.kpfu.itis.android.news.di.application.component

import dagger.Subcomponent
import ru.kpfu.itis.android.news.di.application.scope.ScreenScope
import ru.kpfu.itis.android.news.ui.favorites.FavoritesFragment

@ScreenScope
@Subcomponent
interface FavoritesSComponent {
    fun inject(favoritesFragment: FavoritesFragment)
}