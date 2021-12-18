package com.dev.monk.dogfacts.view.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.SavedFactListItemBinding
import com.dev.monk.dogfacts.usecase.repositories.local.entities.FactEntity
import com.dev.monk.dogfacts.utils.ext.inflateChild

class SavedFactsAdapter : ListAdapter<FactEntity, SavedFactsAdapter.SavedFactViewHolder>(diffCallback) {

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<FactEntity>() {
            override fun areItemsTheSame(oldItem: FactEntity, newItem: FactEntity): Boolean =
                oldItem::class == newItem::class

            override fun areContentsTheSame(oldItem: FactEntity, newItem: FactEntity): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SavedFactViewHolder(SavedFactListItemBinding.bind(parent.inflateChild(R.layout.saved_fact_list_item)))

    override fun onBindViewHolder(holder: SavedFactViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    inner class SavedFactViewHolder(private val binding: SavedFactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fact: FactEntity) {
            binding.factText.text = fact.fact
        }
    }
}