package com.dev.monk.dogfacts.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.MainLayoutBinding
import com.dev.monk.dogfacts.utils.ext.setVisible
import com.dev.monk.dogfacts.view.main.adapters.MainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var binding: MainLayoutBinding

    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter(
            currentFactListener = { fact -> fact?.let(viewModel::onFactSelected) },
            onStateFlowAvailable = { stateFlow ->
                lifecycleScope.launch {
                    stateFlow.collectLatest { states ->
                        adapter?.submitLoadStates(states)
                    }
                }

//                lifecycleScope.launch {
//                    stateFlow
//                        .map { it.refresh }
//                        .distinctUntilChanged()
//                        .collectLatest(viewModel::onRefreshStateChanged)
//                }
            }
        )

        Timber.i("Hello")
        binding.mainPager.adapter = adapter
        binding.mainPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.mainPager) { tab, position ->
            tab.text = if (position == 0) "Facts" else "Saved"
        }.attach()

        binding.mainPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                viewModel.onPageChanged(position)
            }
        })

        binding.heartButton.setOnClickListener {
            viewModel.onHeartClicked()
        }

        subscribeUi()
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.dataSource.collectLatest {
                adapter?.submitNewFacts(it)
            }
        }

        lifecycleScope.launch {
            viewModel.heartButtonState.collectLatest { isSaved ->
                binding.heartButton.setImageResource(
                    if (isSaved) R.drawable.ic_heart
                    else R.drawable.ic_heart_outline
                )
            }
        }

        lifecycleScope.launch {
            viewModel.savedFacts.collectLatest {
                adapter?.submitSavedFacts(it)
            }
        }

        lifecycleScope.launch {
            viewModel.heartButtonVisibility.collectLatest {
                binding.heartButton.setVisible(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}