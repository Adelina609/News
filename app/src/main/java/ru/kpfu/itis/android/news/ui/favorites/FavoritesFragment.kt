package ru.kpfu.itis.android.news.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ru.kpfu.itis.android.news.App
import ru.kpfu.itis.android.news.R
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.di.screens.component.DaggerNewsComponent
import ru.kpfu.itis.android.news.di.screens.module.NewsModule
import ru.kpfu.itis.android.news.di.screens.module.ViewModelModule
import ru.kpfu.itis.android.news.ui.details.DetailsFragment
import ru.kpfu.itis.android.news.utils.ViewModelFactory
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<FavoritesViewModel>
    private var newsViewModel: FavoritesViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerNewsComponent.builder()
            .appComponent(App.getAppComponents())
            .newsModule(NewsModule())
            .viewModelModule(ViewModelModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel::class.java)
        newsViewModel?.getNewsList()
        observeNewsDetailData()
        observeInProgress()
        observeIsSuccess()
    }

    private fun initAdapter() {
        if (rv_news_fr_news.adapter == null) {
            rv_news_fr_news.adapter = NewsAdapter(newsClickListener)
            rv_news_fr_news.layoutManager = LinearLayoutManager(context)
            rv_news_fr_news.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun observeNewsDetailData() {
        newsViewModel?.newsLiveData?.observe(this, Observer {
            (rv_news_fr_news.adapter as NewsAdapter).submitList(it)
        })
    }

    private fun observeInProgress() {
        newsViewModel?.inProgressLiveData?.observe(this, Observer {
            it?.let {
                progressBar_fr_news.visibility =
                    if (it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun observeIsSuccess() {
        newsViewModel?.isSuccessLiveData?.observe(this, Observer {
            makeToast(
                if (it) {
                    getString(R.string.server_news_load_success)
                } else {
                    getString(R.string.server_load_error)
                }
            )
        })
    }

    private val newsClickListener: (News) -> Unit = {
        val args = Bundle()
        args.putString(KEY_NEWS_IMAGE, it.urlToImage)
        args.putString(KEY_NEWS_SOURCE, it.source?.name)
        args.putString(KEY_SOURCE_ID, it.source?.id)
        args.putString(KEY_NEWS_AUTHOR, it.author)
        args.putString(KEY_NEWS_TITLE, it.title)
        args.putString(KEY_NEWS_DESC, it.description)
        args.putString(KEY_NEWS_DATE, it.publishedAt)
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = args
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main_container, detailsFragment)
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun makeToast(text: String) =
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    companion object {
        const val KEY_SOURCE_ID = "id_source"
        const val KEY_NEWS_IMAGE = "news_image"
        const val KEY_NEWS_SOURCE = "news_source"
        const val KEY_NEWS_AUTHOR = "news_author"
        const val KEY_NEWS_TITLE = "news_title"
        const val KEY_NEWS_DESC = "news_desc"
        const val KEY_NEWS_DATE = "news_date"

    }
}