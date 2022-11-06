package com.udacity.asteroidradar.repository


import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.database.AsteroidDatabase
import org.json.JSONObject
import java.util.concurrent.CancellationException

class AsteroidRepository(private val database: AsteroidDatabase){
    private  val TAG = "AsteroidRepository"




       suspend fun getData():List<Asteroid>{

           return database.asteroidDao.getAsteroids()
       }


    suspend fun refreshAsteroids(){
         try {
             val data = AsteroidApi.asteroidInf.getData(
                 getNextSevenDaysFormattedDates().first()
                 , getNextSevenDaysFormattedDates().last(),
                 "JT49uKIxInir0VkUo4U4nOHyUFbWnV3CKZ7OL5UV")
             Log.d("data",data.toString())
             val jsonobj = JSONObject(data)
             val finalData = parseAsteroidsJsonResult(jsonobj)
             database.asteroidDao.insertAll(*finalData.toTypedArray())
             Log.d(TAG, "refreshAsteroids: " + database )
         }catch (ce: CancellationException) {


         } catch (e: Exception) {
             // display error


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