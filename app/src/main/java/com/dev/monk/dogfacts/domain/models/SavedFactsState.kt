package com.dev.monk.dogfacts.domain.models

import com.dev.monk.dogfacts.domain.repositories.local.entities.FactEntity

sealed class SavedFactsState {
    class Item(val item: FactEntity) : SavedFactsState()
    object EMPTY : SavedFactsState()
}
