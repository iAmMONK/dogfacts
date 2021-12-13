package com.dev.monk.dogfacts.usecase.repositories.remote

import com.dev.monk.dogfacts.models.Fact

class DogsApiRepo(private val api: DogsApi): DogsApi {

    override suspend fun getDogFacts(): List<Fact> = api.getDogFacts()
}