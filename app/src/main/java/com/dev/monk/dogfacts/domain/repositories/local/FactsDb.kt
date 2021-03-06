package com.dev.monk.dogfacts.domain.repositories.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev.monk.dogfacts.domain.repositories.local.entities.FactEntity

@Database(entities = [FactEntity::class], version = 1)
abstract class FactsDb : RoomDatabase() {
    abstract fun factsDao(): FactsDao
}
