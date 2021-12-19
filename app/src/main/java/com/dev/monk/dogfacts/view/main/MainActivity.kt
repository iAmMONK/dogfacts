package com.dev.monk.dogfacts.view.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.MainLayoutBinding
import com.dev.monk.dogfacts.view.main.adapters.MainAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var binding: MainLayoutBinding

    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter { fact -> fact?.let(viewModel::onFactSelected) }

        binding.mainPager.adapter = adapter
        binding.mainPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.mainPager) { tab, position ->
            tab.text = if (position == 0) "Facts" else "Saved"
        }.attach()

        binding.mainPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.heartButton.visibility = if (position == 0) View.VISIBLE else View.GONE
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
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}