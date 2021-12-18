package com.dev.monk.dogfacts.view.main

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.FactPageItemBinding
import com.dev.monk.dogfacts.models.Fact
import com.dev.monk.dogfacts.utils.ext.inflateChild

class FactsAdapter : PagingDataAdapter<Fact, FactsAdapter.FactViewHolder>(diffCallback) {

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Fact>() {
            override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean =
                oldItem::class == newItem::class

            override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean =
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

    fun getFact(position: Int): Fact? = getItem(position)

    class FactViewHolder(private val binding: FactPageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showFact(fact: Fact) {
            binding.factText.text = fact.fact
        }
    }
}