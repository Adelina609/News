package ru.kpfu.itis.android.news.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import java.lang.IllegalStateException
import javax.inject.Inject

class ViewModelFactory<T : ViewModel> @Inject constructor(
    private val viewModel: Lazy<T>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModel.get() as T ?: throw IllegalStateException()
}
