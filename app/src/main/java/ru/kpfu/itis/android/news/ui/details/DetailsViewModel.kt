package ru.kpfu.itis.android.news.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.repository.NewsRepository

class DetailsViewModel(val newsRepository: NewsRepository) : ViewModel(), LifecycleObserver {
    private var disposable: Disposable? = null
    val newsLiveData = MutableLiveData<News>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    fun getNewsList(news: News) {
        newsLiveData.value = news
        isSuccessLiveData.value = true
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}