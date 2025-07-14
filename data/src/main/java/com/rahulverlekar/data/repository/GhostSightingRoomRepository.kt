package com.rahulverlekar.data.repository

import com.rahulverlekar.data.db.GhostSightingDao
import com.rahulverlekar.data.mappers.toDomain
import com.rahulverlekar.data.mappers.toEntity
import com.rahulverlekar.domain.models.GhostSighting
import com.rahulverlekar.domain.repository.GhostSightingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GhostSightingRoomRepository @Inject constructor(
     private val dao: GhostSightingDao
): GhostSightingRepository {

    override suspend fun add(item: GhostSighting): GhostSighting {
        val id = dao.insert(item.toEntity())
        item.id = id.toInt()
        return item
    }

    override suspend fun edit(item: GhostSighting): GhostSighting {
        dao.update(item.toEntity())
        return item
    }

    override suspend fun delete(item: GhostSighting): Boolean {
        dao.delete(item.toEntity())
        return true
    }

    override suspend fun getAllSighting(): List<GhostSighting> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun getSighting(id: Int): GhostSighting? {
        val data = dao.loadAllByIds(intArrayOf(id))
        if(data.isEmpty()) {
            return null
        }
        else {
            return data.first().toDomain()
        }
    }


}