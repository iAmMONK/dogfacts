package com.dev.monk.dogfacts.domain.repositories.remote

import com.dev.monk.dogfacts.domain.models.ApiResponse

class DogsApiRepo(private val api: DogsApi) : DogsApi {

    override suspend fun getDogFacts(): ApiResponse = api.getDogFacts()
}
