package ru.kpfu.itis.android.news.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ru.kpfu.itis.android.news.App
import ru.kpfu.itis.android.news.R
import ru.kpfu.itis.android.news.data.entity.News
import ru.kpfu.itis.android.news.utils.ViewModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates

class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<NewsViewModel>
    private var newsViewModel: NewsViewModel by Delegates.notNull()
    lateinit var app: App

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = App()
        app.plusNewsSComponent().inject(this)
    }

    override fun onDestroy() {
        app.clearNewsSComponent()
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)
        arguments?.let {
            val passedArguments = NewsFragmentArgs.fromBundle(it)
            newsViewModel.getNewsList(passedArguments.keysourceid)
        }
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
        newsViewModel.newsLiveData.observe(this, Observer {
            (rv_news_fr_news.adapter as NewsAdapter).submitList(it)
        })
    }

    private fun observeInProgress() {
        newsViewModel.inProgressLiveData.observe(this, Observer {
            it?.let {
                progressBar_fr_news.visibility =
                    if (it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun observeIsSuccess() {
        newsViewModel.isSuccessLiveData.observe(this, Observer {
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
        val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment()
        action.keysourceid = it.source?.id ?: ""
        action.keynewsauthor = it.author ?: ""
        action.keynewsdate = it.publishedAt ?: ""
        action.keynewsdesc = it.description
        action.keynewsimage = it.urlToImage ?: ""
        action.keynewssource = it.source?.name ?: ""
        action.keynewstitle = it.title
        val navController = view?.findNavController()
        navController?.navigate(action)
    }

    private fun makeToast(text: String) =
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
