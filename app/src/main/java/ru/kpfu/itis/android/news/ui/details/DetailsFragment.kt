package ru.kpfu.itis.android.news.ui.details

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news_by_source.*
import ru.kpfu.itis.android.news.App
import ru.kpfu.itis.android.news.R
import ru.kpfu.itis.android.news.di.screens.component.DaggerNewsComponent
import ru.kpfu.itis.android.news.di.screens.module.NewsModule
import ru.kpfu.itis.android.news.di.screens.module.ViewModelModule
import ru.kpfu.itis.android.news.utils.ViewModelFactory
import javax.inject.Inject

class DetailsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_by_source, container, false)
    }

    private var newsViewModel: DetailsViewModel? = null
    @Inject
    lateinit var viewModelFactory: ViewModelFactory<DetailsViewModel>

    lateinit var sourceId :String
    lateinit var sourceName :String
    lateinit var title :String
    lateinit var author :String
    lateinit var urlToImage :String
    lateinit var desc :String
    lateinit var date :String

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerNewsComponent.builder()
            .appComponent(App.getAppComponents())
            .newsModule(NewsModule())
            .viewModelModule(ViewModelModule())
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        sourceId = arguments?.get(KEY_SOURCE_ID) as String
        sourceName = arguments?.get(KEY_NEWS_SOURCE) as String
        title = arguments?.get(KEY_NEWS_TITLE) as String
        author = arguments?.get(KEY_NEWS_AUTHOR) as String
        urlToImage = arguments?.get(KEY_NEWS_IMAGE) as String
        desc = arguments?.get(KEY_NEWS_DESC) as String
        date = arguments?.get(KEY_NEWS_DATE) as String
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cache -> newsViewModel?.cache(sourceId, sourceName, author, title, desc, urlToImage, date)
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
        setUpViews()
//        newsViewModel?.getNews()
//        observeNewsDetailData()
//        observeIsSuccess()
    }

    fun setUpViews() {
        Picasso.get()
            .load(arguments?.get(KEY_NEWS_IMAGE) as String?)
            .into(iv_news_image)
        tv_title_fr_news.text = title
        tv_source_fr_news.text = sourceName
        tv_desc_fr_news.text = desc
        tv_author.text = author
        tv_date.text = date
    }

    private fun observeNewsDetailData() {
        newsViewModel?.newsLiveData?.observe(this, Observer {
           // (rv_news_fr_news.adapter as NewsAdapter).submitList(it)
        })
    }

    private fun observeIsSuccess() {
        newsViewModel?.isSuccessLiveData?.observe(this, Observer {
            makeToast(
                if (it) {
                    getString(R.string.server_load_success)
                } else {
                    getString(R.string.server_load_error)
                }
            )
        })
    }

//    private val newsClickListener: (News) -> Unit = {
//        val args = Bundle()
//        args.putString(KEY_NEWS_IMAGE, it.urlToImage)
//        args.putString(KEY_NEWS_SOURCE, it.source?.name)
//        args.putString(KEY_NEWS_AUTHOR, it.author)
//        args.putString(KEY_NEWS_TITLE, it.title)
//        args.putString(KEY_NEWS_DESC, it.description)
//        args.putString(KEY_NEWS_DATE, it.publishedAt)
//        val detailsFragment = DetailsFragment()
//        detailsFragment.arguments = args
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(R.id.main_container, detailsFragment)
//            ?.addToBackStack(null)
//            ?.commit()
//    }

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