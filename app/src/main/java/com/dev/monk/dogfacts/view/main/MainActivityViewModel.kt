package com.dev.monk.dogfacts.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dev.monk.dogfacts.models.Fact
import com.dev.monk.dogfacts.usecase.facts.FactsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(private val factsManager: FactsManager): ViewModel() {

    val dataSource = factsManager.getPagedFacts()
        .cachedIn(viewModelScope)

    val savedFacts = factsManager.getSavedFacts()

    val heartButtonState: StateFlow<Boolean> get() = _heartButtonState

    private val _heartButtonState = MutableStateFlow(false)

    private var currentFact: Fact? = null

    fun onFactSelected(fact: Fact) {
        currentFact = fact
        checkIfFactIsSaved(fact)
    }

    fun onHeartClicked() {
        viewModelScope.launch {
            currentFact?.let {
                factsManager.saveFact(it)
                checkIfFactIsSaved(it)
            }
        }
    }

    private fun checkIfFactIsSaved(fact: Fact) {
        viewModelScope.launch {
            val isSaved = factsManager.checkIfFactIsSaved(fact)
            _heartButtonState.value = isSaved
        }
    }
}