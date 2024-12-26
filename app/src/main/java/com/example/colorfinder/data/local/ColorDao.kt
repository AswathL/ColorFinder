package com.example.colorfinder.data.local

import androidx.room.*


@Dao
interface ColorDao {
    @Query("SELECT * FROM colors")
    suspend fun getAllColors(): List<ColorEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColor(color: ColorEntry)

    @Delete
    suspend fun deleteColor(color: ColorEntry)

    @Query("DELETE FROM colors")
    suspend fun clearAllColors()

}
