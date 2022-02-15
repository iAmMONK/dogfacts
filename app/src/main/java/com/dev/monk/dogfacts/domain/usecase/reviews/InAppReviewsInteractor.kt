package com.dev.monk.dogfacts.domain.usecase.reviews

import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class InAppReviewsInteractor(
    private val reviewManager: ReviewManager
) {

    companion object {
        private const val PAGE_THRESHOLD = 40
    }

    private val _reviewInfo = MutableSharedFlow<Pair<ReviewManager, ReviewInfo>>()

    val reviewInfo: Flow<Pair<ReviewManager, ReviewInfo>> get() = _reviewInfo

    fun someday(position: Int) {
        if (position % PAGE_THRESHOLD != 0 && position != 0) return

        val reviewFlow = reviewManager.requestReviewFlow()

        reviewFlow.addOnCompleteListener { task ->
            if (task.isSuccessful) return@addOnCompleteListener
            val result = task.result
                _reviewInfo.tryEmit(reviewManager to result)
        }
    }
}
