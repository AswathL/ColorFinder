package com.example.colorfinder.data.repository

import com.example.colorfinder.data.local.ColorDao
import com.example.colorfinder.data.local.ColorEntry
import com.google.firebase.database.FirebaseDatabase

class ColorRepository(private val colorDao: ColorDao) {

    // Fetch all colors

    suspend fun getAllColors(): List<ColorEntry> = colorDao.getAllColors()

    // Insert a new color
    suspend fun insertColor(color: ColorEntry) = colorDao.insertColor(color)

    // Clear all colors
    suspend fun clearColors() = colorDao.clearAllColors()

    // Sync colors to Firebase
    suspend fun syncColorsToFirebase() {
        val colors = colorDao.getAllColors()
        val database = FirebaseDatabase.getInstance("https://color-finder-adc3a-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("colors")
        colors.forEach { color ->
            database.push().setValue(color)
        }
    }
}
