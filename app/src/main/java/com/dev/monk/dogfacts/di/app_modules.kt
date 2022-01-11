package com.dev.monk.dogfacts.di

import com.dev.monk.dogfacts.usecase.ads.AdsManager
import com.dev.monk.dogfacts.usecase.facts.FactsManager
import com.dev.monk.dogfacts.usecase.facts.FactsSource
import com.dev.monk.dogfacts.usecase.repositories.remote.DogsApiRepo
import com.dev.monk.dogfacts.usecase.reviews.InAppReviews
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

    single { FactsManager(get(), get()) }

    factory { FactsSource(get()) }

    single { AdsManager(get(), adRequest) }

    single { InAppReviews(ReviewManagerFactory.create(get())) }
}

private val adRequest = AdRequest.Builder().build()
