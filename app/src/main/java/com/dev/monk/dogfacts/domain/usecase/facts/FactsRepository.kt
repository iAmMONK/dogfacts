package com.dev.monk.dogfacts.domain.usecase.facts

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.dev.monk.dogfacts.domain.models.SavedFactsState
import com.dev.monk.dogfacts.domain.repositories.remote.FactsSource
import com.dev.monk.dogfacts.domain.repositories.local.FactsDao
import com.dev.monk.dogfacts.domain.repositories.local.entities.FactEntity
import com.dev.monk.dogfacts.domain.repositories.remote.DogsApi
import kotlinx.coroutines.flow.map

class FactsRepository(
    private val api: DogsApi,
    private val localRepo: FactsDao
) {

    fun getSavedFacts() = localRepo.getAllFacts()
        .map { it.map { fact -> SavedFactsState.Item(fact) } }
        .map { return@map it.ifEmpty { listOf<SavedFactsState>(SavedFactsState.EMPTY) } }

    fun getPagedFacts() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 200
        )
    ) { FactsSource(api) }
        .flow

    suspend fun checkIfFactIsSaved(fact: String) = localRepo.isFactSaved(fact)

    suspend fun saveFact(fact: String) = localRepo.insertOrRemove(fact)

    suspend fun deleteSavedFact(entity: FactEntity) = localRepo.delete(entity)
}
