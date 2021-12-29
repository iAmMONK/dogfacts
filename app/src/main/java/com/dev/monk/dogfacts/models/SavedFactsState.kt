package com.dev.monk.dogfacts.models

import com.dev.monk.dogfacts.usecase.repositories.local.entities.FactEntity

sealed class SavedFactsState {
    class Item(val item: FactEntity) : SavedFactsState()
    object EMPTY : SavedFactsState()
}
