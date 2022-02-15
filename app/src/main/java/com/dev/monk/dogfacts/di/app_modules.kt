package com.dev.monk.dogfacts.di

import com.dev.monk.dogfacts.domain.usecase.facts.FactsRepository
import com.dev.monk.dogfacts.domain.usecase.ads.AdsInteractor
import com.dev.monk.dogfacts.domain.repositories.remote.FactsSource
import com.dev.monk.dogfacts.domain.repositories.remote.DogsApiRepo
import com.dev.monk.dogfacts.domain.usecase.reviews.InAppReviewsInteractor
import com.dev.monk.dogfacts.view.main.MainActivityViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.play.core.review.ReviewManagerFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { MainActivityViewModel(get(), get(), get()) }
}

val appModule = module {

    single { DogsApiRepo(get()) }

    single { FactsRepository(get(), get()) }

    factory { FactsSource(get()) }

    single { AdsInteractor(get(), adRequest) }

    single { InAppReviewsInteractor(ReviewManagerFactory.create(get())) }
}

private val adRequest = AdRequest.Builder().build()
