package com.dev.monk.dogfacts.view.main.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.SavedFactEmptyListItemBinding
import com.dev.monk.dogfacts.databinding.SavedFactListItemBinding
import com.dev.monk.dogfacts.domain.models.SavedFactsState
import com.dev.monk.dogfacts.domain.repositories.local.entities.FactEntity
import com.dev.monk.dogfacts.utils.ext.inflateChild

class SavedFactsAdapter(
    private val onLongClick: (String) -> Unit
) : ListAdapter<SavedFactsState, RecyclerView.ViewHolder>(diffCallback) {

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<SavedFactsState>() {
            override fun areItemsTheSame(
                oldItem: SavedFactsState,
                newItem: SavedFactsState
            ): Boolean =
                oldItem::class == newItem::class

            override fun areContentsTheSame(
                oldItem: SavedFactsState,
                newItem: SavedFactsState
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == R.layout.saved_fact_list_item)
            SavedFactViewHolder(SavedFactListItemBinding.bind(parent.inflateChild(R.layout.saved_fact_list_item)))
        else
            EmptyViewHolder(SavedFactEmptyListItemBinding.bind(parent.inflateChild(R.layout.saved_fact_empty_list_item)))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SavedFactViewHolder) {
            (getItem(position) as? SavedFactsState.Item)?.let(holder::bind)
        }
    }

    override fun getItemViewType(position: Int) =
        if (getItem(position) is SavedFactsState.Item)
            R.layout.saved_fact_list_item
        else
            R.layout.saved_fact_empty_list_item

    inner class EmptyViewHolder(binding: SavedFactEmptyListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class SavedFactViewHolder(private val binding: SavedFactListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var fact: FactEntity? = null

        fun bind(state: SavedFactsState.Item) {
            fact = state.item
            binding.factText.text = state.item.fact

            binding.root.setOnLongClickListener {
                onLongClick(state.item.fact)
                true
            }
        }
    }
}
