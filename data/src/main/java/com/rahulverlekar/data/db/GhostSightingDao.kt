package com.rahulverlekar.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rahulverlekar.data.entities.GhostSighting
import kotlinx.coroutines.flow.Flow

@Dao
interface GhostSightingDao {

    @Query("SELECT * FROM ghostsighting")
    suspend fun getAll(): List<GhostSighting>

    @Query("SELECT * FROM ghostsighting WHERE id IN (:search)")
    suspend fun loadAllByIds(search: IntArray): List<GhostSighting>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: GhostSighting): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg data: GhostSighting)

    @Update
    suspend fun update(data: GhostSighting):Int

    @Delete
    suspend fun delete(data: GhostSighting): Int
}