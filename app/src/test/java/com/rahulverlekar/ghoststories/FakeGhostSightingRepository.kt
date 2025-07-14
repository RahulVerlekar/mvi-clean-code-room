package com.rahulverlekar.ghoststories

import com.rahulverlekar.domain.models.GhostSighting
import com.rahulverlekar.domain.repository.GhostSightingRepository
import kotlinx.coroutines.delay

class FakeGhostSightingRepository : GhostSightingRepository {

    val data = mutableListOf(
        GhostSighting(1, "Casper", 2),
        GhostSighting(2, "Banshee", 8),
        GhostSighting(3, "WereWolf", 6),
    )
    var simDelay = false

    override suspend fun add(item: GhostSighting): GhostSighting {
        val itemToAdd = if (item.id == 0) {
            val nextId = (data.maxOfOrNull { it.id } ?: 0) + 1
            item.copy(id = nextId)
        } else {
            item
        }
        data.add(itemToAdd)
        return itemToAdd
    }

    override suspend fun edit(item: GhostSighting): GhostSighting {
        val index = data.indexOfFirst { it.id == item.id && item.id != 0 }
        if (index != -1) {
            data[index] = item
        }
        return item
    }

    override suspend fun delete(item: GhostSighting): Boolean {
        return data.removeIf { it.id == item.id && item.id != 0 }
    }

    override suspend fun getAllSighting(): List<GhostSighting> {
        if (simDelay)
        {
            delay(500)
        }
        return data.toList()
    }

    override suspend fun getSighting(id: Int): GhostSighting? {
        return data.find { it.id == id }
    }
}