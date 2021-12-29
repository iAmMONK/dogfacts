package com.dev.monk.dogfacts.view.main.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.FactPageItemBinding
import com.dev.monk.dogfacts.utils.ext.inflateChild

class FactsAdapter : PagingDataAdapter<String, FactsAdapter.FactViewHolder>(diffCallback) {

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem::class == newItem::class

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(
            FactPageItemBinding.bind(parent.inflateChild(R.layout.fact_page_item))
        )
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        getItem(position)?.let(holder::showFact)
    }

    fun getFact(position: Int): String? = if (itemCount > 0) getItem(position) else null

    class FactViewHolder(private val binding: FactPageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showFact(fact: String) {
            binding.factText.text = fact
        }
    }
}
