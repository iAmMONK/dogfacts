package com.dev.monk.dogfacts.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.dev.monk.dogfacts.usecase.facts.FactsManager

class MainActivityViewModel(private val factsManager: FactsManager): ViewModel() {

    val dataSource = factsManager.getPagedFacts()
        .cachedIn(viewModelScope)

}