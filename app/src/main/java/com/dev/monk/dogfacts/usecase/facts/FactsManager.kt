package com.dev.monk.dogfacts.usecase.facts

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dev.monk.dogfacts.usecase.repositories.local.FactsDao
import com.dev.monk.dogfacts.usecase.repositories.local.entities.FactEntity

class FactsManager(
    private val factsSource: FactsSource,
    private val localRepo: FactsDao
) {

    fun getSavedFacts() = localRepo.getAllFacts()

    fun getPagedFacts() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 200
        )
    ) { factsSource }
        .flow

    suspend fun checkIfFactIsSaved(fact: String) = localRepo.isFactSaved(fact)

    suspend fun saveFact(fact: String) = localRepo.insertOrRemove(fact)

    suspend fun deleteSavedFact(entity: FactEntity) = localRepo.delete(entity)
}
