package com.dev.monk.dogfacts.view.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.monk.dogfacts.usecase.facts.FactsManager
import kotlinx.coroutines.launch

class MainActivityViewModel(private val factsManager: FactsManager): ViewModel() {

    init {
        viewModelScope.launch {
            val facts = factsManager.getFacts()
            Log.d("factLogs", facts.toString())
        }
    }
}