package com.udacity.asteroidradar.database

import  android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Query
import com.udacity.asteroidradar.OldData

@Dao
interface AsteroidDao{
    @Query("SELECT * FROM ASTEROID_TABLE_NAME  WHERE  closeApproachDate >= :startDate AND  closeApproachDate <= :endDate ORDER BY  closeApproachDate ASC")
    fun getAsteroidsByDurationDates(startDate: String, endDate: String): List<Asteroid>

    @Query(" SELECT * FROM ASTEROID_TABLE_NAME")
    fun getAsteroids():List<Asteroid>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg oldData: Asteroid)
}




@Database(entities = [Asteroid::class], version = 1)
abstract class AsteroidDatabase:RoomDatabase(){
    abstract val asteroidDao : AsteroidDao
}

private lateinit var INSTANCE:AsteroidDatabase

fun getDatabase(context: Context):AsteroidDatabase {
    synchronized(AsteroidDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AsteroidDatabase::class.java,
                "asteroid").build()
        }
    }
    return INSTANCE
}