package com.dev.monk.dogfacts.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.dev.monk.dogfacts.models.SavedFactsState
import com.dev.monk.dogfacts.usecase.facts.FactsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivityViewModel(private val factsManager: FactsManager) : ViewModel() {

    val dataSource = factsManager.getPagedFacts()
        .cachedIn(viewModelScope)

    val savedFacts = factsManager.getSavedFacts()
        .map { it.map { fact -> SavedFactsState.Item(fact) } }
        .map {
            return@map if (it.isEmpty())
                listOf<SavedFactsState>(SavedFactsState.EMPTY)
            else
                it
        }

    val heartButtonState: StateFlow<Boolean> get() = _heartButtonState
    val heartButtonVisibility: StateFlow<Boolean> get() = _heartButtonVisibility

    private val _heartButtonState = MutableStateFlow(false)
    private val _heartButtonVisibility = MutableStateFlow(false)

    private var currentFact: String? = null
    private var isSuccessState: Boolean = false
    private var isOnRemotePage: Boolean = false

    fun onFactSelected(fact: String) {
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

    fun onPageChanged(position: Int) {
        isOnRemotePage = position == 0
        emitHeartButtonVisibility()
    }

    fun onRefreshStateChanged(loadState: LoadState) {
        isSuccessState = loadState is LoadState.NotLoading
        emitHeartButtonVisibility()
    }

    private fun emitHeartButtonVisibility() {
        viewModelScope.launch {
            _heartButtonVisibility.emit(isSuccessState && isOnRemotePage)
        }
    }

    private fun checkIfFactIsSaved(fact: String) {
        viewModelScope.launch {
            val isSaved = factsManager.checkIfFactIsSaved(fact)
            _heartButtonState.value = isSaved
        }
    }
}
