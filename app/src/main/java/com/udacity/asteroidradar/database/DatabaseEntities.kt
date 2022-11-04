package com.udacity.asteroidradar.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "ASTEROID_TABLE_NAME")
@Parcelize
data class Asteroid(
    @PrimaryKey
   val id: Long,
     val codeName: String,
     val closeApproachDate: String,
     val absoluteMagnitude: Double,
     val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean) : Parcelable





