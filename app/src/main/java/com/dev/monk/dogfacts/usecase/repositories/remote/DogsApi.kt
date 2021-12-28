package com.dev.monk.dogfacts.usecase.repositories.remote

import com.dev.monk.dogfacts.models.ApiResponse
import com.dev.monk.dogfacts.models.Fact
import retrofit2.http.GET

interface DogsApi {

    @GET("facts?number=10")
    suspend fun getDogFacts(): ApiResponse
}