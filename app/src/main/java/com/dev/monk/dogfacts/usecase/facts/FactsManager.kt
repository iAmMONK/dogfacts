package com.dev.monk.dogfacts.usecase.facts

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dev.monk.dogfacts.models.Fact
import com.dev.monk.dogfacts.usecase.repositories.remote.DogsApiRepo
import java.util.concurrent.Flow

class FactsManager(
    private val repo: DogsApiRepo,
    private val factsSource: FactsSource
) {

    suspend fun getFacts() = repo.getDogFacts()

    fun getPagedFacts() = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 200
        )
    ) { factsSource }
        .flow

}