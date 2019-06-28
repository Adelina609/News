package ru.kpfu.itis.android.news.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kpfu.itis.android.news.data.local.model.NewsDB
import io.reactivex.Single
import ru.kpfu.itis.android.news.data.network.model.Source

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsList(news: NewsDB)

    @Query("SELECT * FROM news_data")
    fun getNewsList(): Single<List<NewsDB>>

    @Query("SELECT * FROM news_data WHERE id = :id")
    fun findNewsById(id: Int): Single<NewsDB>

    @Query("SELECT * FROM news_data WHERE source = :author AND title =:title")
    fun findNewsByAuthorAndTitle(author : String?, title : String) : List<NewsDB>

    @Query("DELETE FROM news_data")
    fun deleteAllNews()
}
