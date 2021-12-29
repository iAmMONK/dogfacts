package com.dev.monk.dogfacts.di

import com.dev.monk.dogfacts.usecase.facts.FactsManager
import com.dev.monk.dogfacts.usecase.facts.FactsSource
import com.dev.monk.dogfacts.usecase.repositories.remote.DogsApiRepo
import com.dev.monk.dogfacts.view.main.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { MainActivityViewModel(get()) }
}

val appModule = module {

    single { DogsApiRepo(get()) }

    single { FactsManager(get(), get()) }

    factory { FactsSource(get()) }
}
