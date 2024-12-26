package com.example.colorfinder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
data class ColorEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val color: String,
    val time: Long
)
