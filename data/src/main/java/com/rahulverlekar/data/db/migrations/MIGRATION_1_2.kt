package com.rahulverlekar.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
  override fun migrate(database: SupportSQLiteDatabase) {
    database.execSQL("ALTER TABLE `GhostSighting` ADD COLUMN `is_confirmed` INTEGER NOT NULL DEFAULT 0")
  }
}