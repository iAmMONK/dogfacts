package com.dev.monk.dogfacts.view.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.dev.monk.dogfacts.domain.repositories.local.entities.FactEntity
import com.dev.monk.dogfacts.domain.usecase.ads.AdsInteractor
import com.dev.monk.dogfacts.domain.usecase.facts.FactsRepository
import com.dev.monk.dogfacts.domain.usecase.reviews.InAppReviewsInteractor
import com.google.android.gms.ads.interstitial.InterstitialAd
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val factsManager: FactsRepository,
    private val adsManager: AdsInteractor,
    private val inAppReviews: InAppReviewsInteractor
) : ViewModel() {

    val dataSource = factsManager.getPagedFacts()
        .cachedIn(viewModelScope)

    val savedFacts = factsManager.getSavedFacts()

    val reviewInfo get() = inAppReviews.reviewInfo
    val heartButtonState: StateFlow<Boolean> get() = _heartButtonState
    val heartButtonVisibility: StateFlow<Boolean> get() = _heartButtonVisibility
    val interAds: SharedFlow<InterstitialAd?> get() = adsManager.interAdFlow

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

    fun onFactsPageChanged(position: Int) {
        adsManager.onFactsPageChanged(position)
        inAppReviews.someday(position)
    }

    fun onRefreshStateChanged(loadState: LoadState) {
        isSuccessState = loadState is LoadState.NotLoading
        emitHeartButtonVisibility()
    }

    fun onSavedFactSwiped(factEntity: FactEntity) {
        viewModelScope.launch {
            factsManager.deleteSavedFact(factEntity)
        }
    }

    private fun emitHeartButtonVisibility() {
        _heartButtonVisibility.tryEmit(isSuccessState && isOnRemotePage)
    }

    private fun checkIfFactIsSaved(fact: String) {
        viewModelScope.launch {
            val isSaved = factsManager.checkIfFactIsSaved(fact)
            _heartButtonState.value = isSaved
        }
    }
}
