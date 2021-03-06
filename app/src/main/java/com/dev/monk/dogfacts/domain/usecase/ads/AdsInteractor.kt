package com.dev.monk.dogfacts.domain.usecase.ads

import android.content.Context
import com.dev.monk.dogfacts.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class AdsInteractor(
    private val context: Context,
    private val adRequest: AdRequest
) {

    companion object {
        private const val PAGE_THRESHOLD = 20
    }

    private val _interAdFlow: MutableSharedFlow<InterstitialAd?> = MutableSharedFlow(replay = 1)

    val interAdFlow: SharedFlow<InterstitialAd?> get() = _interAdFlow

    fun onFactsPageChanged(position: Int) {
        if (position == 0 || position % PAGE_THRESHOLD != 0) return

        InterstitialAd.load(
            context, context.getString(R.string.admob_ad_unit_id), adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    _interAdFlow.tryEmit(ad)
                }
            }
        )
    }
}
