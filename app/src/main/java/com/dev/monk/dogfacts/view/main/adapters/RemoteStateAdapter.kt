package com.dev.monk.dogfacts.view.main.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.RemoteFactsStateItemBinding
import com.dev.monk.dogfacts.utils.ext.inflateChild
import com.dev.monk.dogfacts.utils.ext.setVisible

class RemoteStateAdapter(private val retryFunc: () -> Unit) : LoadStateAdapter<RemoteStateAdapter.StateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateViewHolder =
        StateViewHolder(RemoteFactsStateItemBinding.bind(parent.inflateChild(R.layout.remote_facts_state_item)))

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class StateViewHolder(private val binding: RemoteFactsStateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retryFunc() }
        }

        fun bind(state: LoadState) {
            binding.errorLayout.setVisible(state is LoadState.Error)
            binding.progressBar.setVisible(state is LoadState.Loading)
        }
    }
}
