package com.dev.monk.dogfacts.usecase.facts

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dev.monk.dogfacts.usecase.repositories.local.FactsDao
import com.dev.monk.dogfacts.usecase.repositories.local.entities.FactEntity
import com.dev.monk.dogfacts.usecase.repositories.remote.DogsApiRepo

class FactsManager(
    private val repo: DogsApiRepo,
    private val factsSource: FactsSource,
    private val localRepo: FactsDao
) {

    suspend fun getFacts() = repo.getDogFacts()

    fun getPagedFacts() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 200
        )
    ) { factsSource }
        .flow

    suspend fun saveFact(fact: String) = localRepo.insertAll(FactEntity(fact = fact))
}