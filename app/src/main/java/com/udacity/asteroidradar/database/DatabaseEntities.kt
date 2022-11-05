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
 @ColumnInfo
 val id: Long,
 @ColumnInfo

 val codeName: String,
 @ColumnInfo

 val closeApproachDate: String,
 @ColumnInfo

 val absoluteMagnitude: Double,
 @ColumnInfo

 val estimatedDiameter: Double,

 @ColumnInfo
 val relativeVelocity: Double,

 @ColumnInfo
 val distanceFromEarth: Double,


 @ColumnInfo
 val isPotentiallyHazardous: Boolean) : Parcelable





