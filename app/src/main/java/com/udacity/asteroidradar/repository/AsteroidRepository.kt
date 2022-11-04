package com.udacity.asteroidradar.repository

import android.util.Log
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.AsteroidDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidDatabase){
    private  val TAG = "AsteroidRepository"


    suspend fun getData(){
    withContext(Dispatchers.IO){
        database.asteroidDao.getAsteroids()
    }
}

    suspend fun refreshAsteroids(){
        withContext(Dispatchers.IO){
                  val data = AsteroidApi.asteroidInf.getData("2022-11-4","JT49uKIxInir0VkUo4U4nOHyUFbWnV3CKZ7OL5UV")
                  val jsonobj = JSONObject(data)
                   val finalData = parseAsteroidsJsonResult(jsonobj)
            database.asteroidDao.insertAll(finalData)
            Log.d(TAG, "refreshAsteroids: " + database )

        }
    }











// suspend fun getData(){
//
//    val result = asteroidService.getData("2022-11-03",
//        "JT49uKIxInir0VkUo4U4nOHyUFbWnV3CKZ7OL5UV")
//     val jsonObj= JSONObject(result)
//     val data = parseAsteroidsJsonResult(jsonObj)
//     database.asteroidDao.getAsteroids(data)
//
//}

}