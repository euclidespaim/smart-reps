package com.cyberkidstudios.smartreps.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey val id: String = "singleton",
    val weightUnit: String, // "kg", "lbs"
    val language: String, // "pt", "en", "auto"
    val defaultRestSeconds: Int,
    val isPremium: Boolean,
    val premiumExpiresAt: Long?
)
