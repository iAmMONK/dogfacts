package com.dev.monk.dogfacts.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity: AppCompatActivity() {

    private val viewModel by viewModel<MainActivityViewModel>()

    private var adapter: FactsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = FactsAdapter()

        viewModel

        subscribeUi()
    }

    private fun subscribeUi() {
        lifecycleScope.launch {
            viewModel.dataSource.collectLatest { adapter?.submitData(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter = null
    }

}