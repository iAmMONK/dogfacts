package com.dev.monk.dogfacts.usecase.repositories.local

import androidx.room.*
import com.dev.monk.dogfacts.usecase.repositories.local.entities.FactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FactsDao {
    @Query("SELECT * FROM saved_facts")
    fun getAllFacts(): Flow<List<FactEntity>>

    @Query("SELECT * FROM saved_facts WHERE fact = :fact")
    suspend fun getFact(fact: String): FactEntity?

    @Insert
    suspend fun insertAll(vararg fact: FactEntity)

    @Transaction
    suspend fun insertOrRemove(fact: String) {
        val entity = getFact(fact)
        if (entity == null) {
            insertAll(FactEntity(fact = fact))
        } else {
            delete(entity)
        }
    }

    @Query("SELECT EXISTS(SELECT * FROM saved_facts WHERE fact = :factText)")
    suspend fun isFactSaved(factText: String) : Boolean

    @Delete
    suspend fun delete(fact: FactEntity)

}