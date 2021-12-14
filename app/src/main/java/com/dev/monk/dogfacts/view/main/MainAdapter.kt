package com.dev.monk.dogfacts.view.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.FactsPageBinding
import com.dev.monk.dogfacts.utils.ext.inflateChild

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FactsViewHolder(FactsPageBinding.bind(parent.inflateChild(R.layout.facts_page)))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // TODO
    }

    override fun getItemCount() = 2

    private inner class FactsViewHolder(private val binding: FactsPageBinding) :
        RecyclerView.ViewHolder(binding.root)
}