package com.dev.monk.dogfacts.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dev.monk.dogfacts.databinding.MainLayoutBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()
    private lateinit var binding: MainLayoutBinding

    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MainAdapter()

        binding.mainPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.mainPager) { tab, position ->
            tab.text = if (position == 0) "Facts" else "Saved"
        }.attach()

        subscribeUi()
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.dataSource.collectLatest {
//                adapter?.submitData(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}