package com.dev.monk.dogfacts.usecase.repositories.remote

import com.dev.monk.dogfacts.models.ApiResponse

class DogsApiRepo(private val api: DogsApi): DogsApi {

    override suspend fun getDogFacts(): ApiResponse = api.getDogFacts()
}