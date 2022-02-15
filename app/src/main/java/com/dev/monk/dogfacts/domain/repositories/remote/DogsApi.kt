package com.dev.monk.dogfacts.domain.repositories.remote

import com.dev.monk.dogfacts.domain.models.ApiResponse
import retrofit2.http.GET

interface DogsApi {

    @GET("facts?number=10")
    suspend fun getDogFacts(): ApiResponse
}
