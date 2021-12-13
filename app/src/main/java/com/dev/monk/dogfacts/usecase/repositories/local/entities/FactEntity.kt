package com.dev.monk.dogfacts.usecase.repositories.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_facts")
data class FactEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "fact") var fact: String,
)
