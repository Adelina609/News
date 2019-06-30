package ru.kpfu.itis.android.news.ui.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_news_by_source.*
import ru.kpfu.itis.android.news.App
import ru.kpfu.itis.android.news.R
import kotlin.properties.Delegates

class DetailsFragment : Fragment() {

    private var detailsViewModel: DetailsViewModel by Delegates.notNull()
    lateinit var app: App

    private lateinit var sourceId: String
    private lateinit var sourceName: String
    private lateinit var title: String
    private lateinit var author: String
    private lateinit var urlToImage: String
    private lateinit var desc: String
    private lateinit var date: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            val passedArguments = DetailsFragmentArgs.fromBundle(it)
            sourceId = passedArguments.keysourceid
            sourceName = passedArguments.keynewssource
            title = passedArguments.keynewstitle
            author = passedArguments.keynewsauthor
            urlToImage = passedArguments.keynewsimage
            desc = passedArguments.keynewsdesc
            date = passedArguments.keynewsdate
        }
        app = App()
        app.plusDetailsSComponent().inject(this)
    }

    override fun onDestroy() {
        app.clearDetailsSComponent()
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_by_source, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_cache -> detailsViewModel.cache(sourceId, sourceName, author, title, desc, urlToImage, date)
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        Picasso.get()
            .load(urlToImage)
            .into(iv_news_image)
        tv_title_fr_news.text = title
        tv_source_fr_news.text = sourceName
        tv_desc_fr_news.text = desc
        tv_author.text = author
        tv_date.text = date
    }
}