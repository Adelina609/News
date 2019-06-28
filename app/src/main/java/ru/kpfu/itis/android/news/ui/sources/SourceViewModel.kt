package ru.kpfu.itis.android.news.ui.sources

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import ru.kpfu.itis.android.news.data.entity.Source
import ru.kpfu.itis.android.news.data.repository.NewsRepository

class SourceViewModel(val newsRepository: NewsRepository) : ViewModel(), LifecycleObserver {
    private var disposable: Disposable? = null
    val sourceLiveData = MutableLiveData<List<Source>>()
    val inProgressLiveData = MutableLiveData<Boolean>()
    var isSuccessLiveData = MutableLiveData<Boolean>()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getSourcesList() {
        disposable = newsRepository.getSources()
            .doOnSubscribe {
                inProgressLiveData.postValue(true)
            }
            .doAfterTerminate {
                inProgressLiveData.postValue(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    sourceLiveData.value = it
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