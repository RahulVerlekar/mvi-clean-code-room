package com.rahulverlekar.data.mappers

import com.rahulverlekar.data.entities.GhostSighting

fun GhostSighting.toDomain(): com.rahulverlekar.domain.models.GhostSighting {
    return com.rahulverlekar.domain.models.GhostSighting(id, name, scariness)
}

fun com.rahulverlekar.domain.models.GhostSighting.toEntity(): GhostSighting {
    return GhostSighting(id, name, scariness)
}
