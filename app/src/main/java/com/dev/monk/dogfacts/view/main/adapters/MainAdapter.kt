package com.dev.monk.dogfacts.view.main.adapters

import android.view.View
import android.view.ViewGroup
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.RemoteFactsListItemBinding
import com.dev.monk.dogfacts.databinding.SavedFactsListItemBinding
import com.dev.monk.dogfacts.models.SavedFactsState
import com.dev.monk.dogfacts.utils.ext.inflateChild
import kotlinx.coroutines.flow.Flow

class MainAdapter(
    private val currentFactListener: (String?, Int) -> Unit,
    private val onStateFlowAvailable: (Flow<CombinedLoadStates>) -> Unit
) : RecyclerView.Adapter<MainAdapter.BaseViewHolder>() {

    private val factsAdapter = RemoteFactsAdapter()
    private val factsHeaderAdapter = RemoteStateAdapter { factsAdapter.retry() }
    private val factsFooterAdapter = RemoteStateAdapter { factsAdapter.retry() }
    private val savedFactsAdapter = SavedFactsAdapter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        if (viewType == R.layout.remote_facts_list_item)
            FactsViewHolder(RemoteFactsListItemBinding.bind(parent.inflateChild(R.layout.remote_facts_list_item)))
        else
            SavedFactsViewHolder(SavedFactsListItemBinding.bind(parent.inflateChild(R.layout.saved_facts_list_item)))

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setup()
    }

    override fun getItemCount() = 2

    override fun getItemViewType(position: Int) =
        if (position == 0) R.layout.remote_facts_list_item else R.layout.saved_facts_list_item

    suspend fun submitNewFacts(data: PagingData<String>) {
        factsAdapter.submitData(data)
    }

    fun submitSavedFacts(facts: List<SavedFactsState>) {
        savedFactsAdapter.submitList(facts)
    }

    fun submitLoadStates(states: CombinedLoadStates) {
        factsHeaderAdapter.loadState = states.refresh
        factsFooterAdapter.loadState = states.append
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun setup()
    }

    private inner class FactsViewHolder(private val binding: RemoteFactsListItemBinding) :
        BaseViewHolder(binding.root) {

        override fun setup() {
            binding.root.adapter = factsAdapter.withLoadStateHeaderAndFooter(
                factsHeaderAdapter,
                factsFooterAdapter
            )
            binding.root.offscreenPageLimit = 5
            binding.root.setPageTransformer(StackPageTransformer(5))

            onStateFlowAvailable(factsAdapter.loadStateFlow)

            binding.root.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    currentFactListener(factsAdapter.getFact(position), position)
                }
            })
        }
    }

    private inner class SavedFactsViewHolder(private val binding: SavedFactsListItemBinding) :
        BaseViewHolder(binding.root) {

        override fun setup() {
            binding.root.adapter = savedFactsAdapter
        }
    }
}
