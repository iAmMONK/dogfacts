package com.dev.monk.dogfacts.view.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev.monk.dogfacts.models.Fact
//
//class SavedFactsAdapter: ListAdapter<Fact, RecyclerView.ViewHolder>(diffCallback) {
//
//    private companion object {
//        val diffCallback = object : DiffUtil.ItemCallback<Fact>() {
//            override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean =
//                oldItem::class == newItem::class
//
//            override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean =
//                oldItem == newItem
//
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
//        FactViewHolder(View(parent.context))
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//    }
//}