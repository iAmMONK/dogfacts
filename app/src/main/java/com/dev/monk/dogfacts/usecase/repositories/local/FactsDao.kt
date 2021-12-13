package com.dev.monk.dogfacts.usecase.repositories.local

import androidx.room.*
import com.dev.monk.dogfacts.usecase.repositories.local.entities.FactEntity

@Dao
interface FactsDao {
    @Query("SELECT * FROM saved_facts")
    suspend fun getAll(): List<FactEntity>

    @Insert
    suspend fun insertAll(vararg fact: FactEntity)

    @Delete
    suspend fun delete(fact: FactEntity)

}