package ru.kpfu.itis.android.news.ui.news

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.repository.NewsRepository

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel(), LifecycleObserver {
    private var disposable: Disposable? = null
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgressLiveData = MutableLiveData<Boolean>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    fun getNewsList(source : String) {
        disposable = newsRepository.getNewsBySource(source)
            .doOnSubscribe {
                inProgressLiveData.postValue(true)
            }
            .doAfterTerminate {
                inProgressLiveData.postValue(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    newsLiveData.value = it
                    isSuccessLiveData.value = true
                },
                onError = {
                    println("---------------------------------" + it.message)
                    isSuccessLiveData.value = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}