package com.dev.monk.dogfacts.di

import android.content.Context
import androidx.room.Room
import com.dev.monk.dogfacts.domain.repositories.local.FactsDb
import com.dev.monk.dogfacts.domain.repositories.remote.DogsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val URL = "https://dog-api.kinduff.com/api/"

val remoteModule = module {
    single { dogFactsService }
}

val localModule = module {
    single { getDb(get()).factsDao() }
}

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(
        HttpLoggingInterceptor(Timber::i).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    )
    .callTimeout(30, TimeUnit.SECONDS)
    .build()

private val buildRetrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(httpClient)
    .baseUrl(URL)
    .build()

private val dogFactsService = buildRetrofit.create(DogsApi::class.java)

private fun getDb(context: Context) =
    Room.databaseBuilder(context, FactsDb::class.java, "saved-facts.db")
        .build()
