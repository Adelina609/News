package ru.kpfu.itis.android.news.ui.sources

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_source.view.*
import ru.kpfu.itis.android.news.R
import ru.kpfu.itis.android.news.data.entity.Source

class SourceAdapter(
    private val sourceLambda: (Source) -> Unit
) :
    ListAdapter<Source, SourceAdapter.SourceHolder>(SourceDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceAdapter.SourceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_source, parent, false)
        return SourceHolder(view)
    }

    override fun onBindViewHolder(holder: SourceAdapter.SourceHolder, position: Int) {
        holder.bind(getItem(position), sourceLambda)
    }

    class SourceDiffCallback : DiffUtil.ItemCallback<Source>() {
        override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean = oldItem == newItem

    }

    class SourceHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(source : Source, clickListener: (Source) -> Unit) {
            with(containerView) {
                containerView.tv_source_name.text = source.name
                containerView.tv_source_desc.text = source.description
                setOnClickListener { clickListener(source) }
            }
            //Picasso.get().load(source.urlToImage).into(iv_main_image)
        }
    }
}