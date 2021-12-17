package com.dev.monk.dogfacts.view.main

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.RemoteFactsListItemBinding
import com.dev.monk.dogfacts.models.Fact
import com.dev.monk.dogfacts.utils.ext.inflateChild

class MainAdapter : RecyclerView.Adapter<MainAdapter.BaseViewHolder>() {

    private val factsAdapter = FactsAdapter()
//    private val savedFactsAdapter = SavedFactsAdapter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        FactsViewHolder(RemoteFactsListItemBinding.bind(parent.inflateChild(R.layout.remote_facts_list_item)))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setup()
    }

    override fun getItemCount() = 2

    suspend fun submitNewFacts(data: PagingData<Fact>) {
        factsAdapter.submitData(data)
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun setup()
    }

    private inner class FactsViewHolder(private val binding: RemoteFactsListItemBinding) :
        BaseViewHolder(binding.root) {

        override fun setup() {
            binding.root.adapter = factsAdapter
        }
    }
}