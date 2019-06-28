package ru.kpfu.itis.android.news.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.local.model.CachedNews
import ru.kpfu.itis.android.news.data.network.model.Source
import ru.kpfu.itis.android.news.data.repository.NewsRepository

class DetailsViewModel(val newsRepository: NewsRepository) : ViewModel(), LifecycleObserver {
    private var disposable: Disposable? = null
    val newsLiveData = MutableLiveData<News>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    fun cache(sourceId: String, sourceName : String,  author : String,  title : String,
              desc : String, urlToImage : String, publishedAt : String){
        val cachedNews = CachedNews(Source(sourceId, sourceName), author, title, desc, urlToImage, publishedAt)
        newsRepository.cacheFavoriteNews(cachedNews)
            //.observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    isSuccessLiveData.value = true},
                onError = {
                    println("---------------------------------" + it.message)
                    isSuccessLiveData.value = false
                }
            )
    }
    fun getNews(news: News) {
        newsLiveData.value = news
        isSuccessLiveData.value = true
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}