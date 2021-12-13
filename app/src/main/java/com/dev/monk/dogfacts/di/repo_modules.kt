package com.dev.monk.dogfacts.di

import android.content.Context
import androidx.room.Room
import com.dev.monk.dogfacts.usecase.repositories.local.FactsDb
import com.dev.monk.dogfacts.usecase.repositories.remote.DogsApi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val URL = "https://dog-facts-api.herokuapp.com/api/v1/resources/"

val remoteModule = module {

    single { dogFactsService }

}

val localModule = module {
    single { getDb(get()).factsDao() }
}

private val httpClient = OkHttpClient.Builder()
    .callTimeout(30, TimeUnit.SECONDS)
    .build()

private val buildRetrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(httpClient)
    .baseUrl(URL)
    .build()

private val dogFactsService = buildRetrofit.create(DogsApi::class.java)

private fun getDb(context: Context) =
    Room.databaseBuilder(
        context,
        FactsDb::class.java,
        "todo-list.db"
    ).build()