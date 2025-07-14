package com.rahulverlekar.domain.repository

import com.rahulverlekar.domain.models.GhostSighting

interface GhostSightingRepository: Repository {

    suspend fun add(item: GhostSighting): GhostSighting

    suspend fun edit(item: GhostSighting): GhostSighting

    suspend fun delete(item: GhostSighting): Boolean

    suspend fun getAllSighting(): List<GhostSighting>

    suspend fun getSighting(id: Int): GhostSighting?
}