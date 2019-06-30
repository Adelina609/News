package ru.kpfu.itis.android.news.ui.sources

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
import kotlinx.android.synthetic.main.fragment_sources.*
import ru.kpfu.itis.android.news.App
import ru.kpfu.itis.android.news.R
import ru.kpfu.itis.android.news.data.entity.Source
import ru.kpfu.itis.android.news.di.screens.component.DaggerNewsComponent
import ru.kpfu.itis.android.news.di.screens.module.NewsModule
import ru.kpfu.itis.android.news.di.screens.module.ViewModelModule
import ru.kpfu.itis.android.news.utils.ViewModelFactory
import javax.inject.Inject
import kotlin.properties.Delegates

class SourcesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SourceViewModel>
    private var sourcesListViewModel: SourceViewModel by Delegates.notNull()

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
        return inflater.inflate(R.layout.fragment_sources, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        sourcesListViewModel = ViewModelProviders.of(this, viewModelFactory).get(SourceViewModel::class.java)
        observeNewsLiveData()
        observeInProgress()
        observeIsSuccess()
        lifecycle.addObserver(sourcesListViewModel)
    }

    private fun observeNewsLiveData() {
        sourcesListViewModel.sourceLiveData.observe(this, Observer {
            (rv_sources.adapter as SourceAdapter).submitList(it)
        })
    }

    private fun initAdapter() {
        if (rv_sources.adapter == null) {
            rv_sources.adapter = SourceAdapter(sourceClickListener)
            rv_sources.layoutManager = LinearLayoutManager(context)
            rv_sources.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun observeInProgress() {
        sourcesListViewModel.inProgressLiveData.observe(this, Observer {
            it?.let {
                progressBar_fr_sources.visibility =
                    if (it) View.VISIBLE else View.GONE
            }
        })
    }

    private fun observeIsSuccess() {
        sourcesListViewModel.isSuccessLiveData.observe(this, Observer {
            makeToast(
                if (it) {
                    getString(R.string.server_sources_load_success)
                } else {
                    getString(R.string.server_load_error)
                }
            )
        })
    }

    private fun makeToast(text: String) =
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

    private val sourceClickListener: (Source) -> Unit = {
        val action = SourcesFragmentDirections.actionSourcesFragmentToNewsFragment()
        action.keysourceid = it.id
        val navController = view?.findNavController()
        navController?.navigate(action)
    }
}
