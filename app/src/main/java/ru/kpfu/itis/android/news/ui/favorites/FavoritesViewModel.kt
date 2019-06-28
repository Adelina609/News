package ru.kpfu.itis.android.news.ui.favorites

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.data.repository.NewsRepository

class FavoritesViewModel(val newsRepository: NewsRepository) : ViewModel(), LifecycleObserver {
    private var disposable: Disposable? = null
    val newsLiveData = MutableLiveData<List<News>>()
    val inProgressLiveData = MutableLiveData<Boolean>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getNewsList() {
        disposable = newsRepository.getFavoriteNews()
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
                    isSuccessLiveData.value = false
                }
            )
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}