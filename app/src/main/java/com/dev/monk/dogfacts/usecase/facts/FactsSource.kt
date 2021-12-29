package com.dev.monk.dogfacts.usecase.facts

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev.monk.dogfacts.usecase.repositories.remote.DogsApiRepo
import java.lang.Exception

class FactsSource(private val repo: DogsApiRepo) : PagingSource<Int, String>() {

    override fun getRefreshKey(state: PagingState<Int, String>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            val result = repo.getDogFacts().facts
            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = (params.key ?: 0) + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
