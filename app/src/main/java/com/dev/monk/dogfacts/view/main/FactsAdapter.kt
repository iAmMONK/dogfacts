package com.dev.monk.dogfacts.view.main

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.dev.monk.dogfacts.models.Fact

class FactsAdapter: PagingDataAdapter<Fact, FactViewHolder>(diffCallback) {

    private companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Fact>() {
            override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean =
                oldItem::class == newItem::class

            override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        return FactViewHolder(View(parent.context))
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        // do stuff
    }

}