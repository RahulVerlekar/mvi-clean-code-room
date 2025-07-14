package com.rahulverlekar.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GhostSighting(
    @PrimaryKey(true) val id: Int = 0,
    @ColumnInfo var name: String,
    @ColumnInfo var scariness: Int,
    @ColumnInfo(name = "is_confirmed", defaultValue = "0")
    val isConfirmed: Boolean = false
)