package com.dev.monk.dogfacts.usecase.facts

import com.dev.monk.dogfacts.usecase.repositories.remote.DogsApiRepo

class FactsManager(private val repo: DogsApiRepo) {

    suspend fun getFacts() = repo.getDogFacts()

}