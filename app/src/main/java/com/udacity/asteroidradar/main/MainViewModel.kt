package com.udacity.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.udacity.asteroidradar.OldData
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.ImageApi
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import com.udacity.asteroidradar.database.Asteroid
import com.udacity.asteroidradar.database.getDatabase
import com.udacity.asteroidradar.repository.AsteroidRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.lang.Exception

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "MainViewModel"



    private val database = getDatabase(application)
    private val repository = AsteroidRepository(database)

    private val _status = MutableLiveData<String>()
    val status:LiveData<String>
        get() = _status

    private val _navigateToDetailData = MutableLiveData<Asteroid?>()
      val navigateToDetailData: LiveData<Asteroid?>
       get() = _navigateToDetailData


    private val _property = repository.property

    val property:LiveData<List<Asteroid>>
        get() = _property



    private val _imageUrl = MutableLiveData<PictureOfDay>()
    val imageUrl:LiveData<PictureOfDay>
        get() =_imageUrl
    init {
        viewModelScope.launch {
            repository.refreshAsteroids()
            repository.getData()
        }
        getImageOfDay()
    }


//private fun getAsteroidData(){
//    viewModelScope.launch(Dispatchers.IO) {
//          try {
//              val listResult = AsteroidApi.asteroidInf.getData("2022-11-03",
//                  "JT49uKIxInir0VkUo4U4nOHyUFbWnV3CKZ7OL5UV")
//                Log.i(TAG, "getAsteroidData: " + listResult)
//                println(listResult)
//               val jsonObj=JSONObject(listResult)
//              Log.d(TAG, "getAsteroidData:" + jsonObj)
//                  _property.postValue( parseAsteroidsJsonResult(jsonObj))
//              Log.i(TAG, "getAsteroid:" + parseAsteroidsJsonResult(jsonObj) )
//              Log.d(TAG, "getAsteroidData: " + _property.value)
//
//          }catch (e:Exception){
//              _status.postValue("Failure: ${e.message}")
//              Log.e(TAG, "getAsteroidData: " + e.message )
//          }
//    }
// }

    private fun getImageOfDay(){
        viewModelScope.launch {
            try{
                val result = ImageApi.imageData.getImage("JT49uKIxInir0VkUo4U4nOHyUFbWnV3CKZ7OL5UV")
                if(result.mediaType == "image")

                    _imageUrl.value = result
                Log.i(TAG, "getImageOfDay: " + _imageUrl)
            }catch (e:Exception){
                _status.value ="Failure: ${e.message}"
                Log.e(TAG, "getImageOfDay: " + e.message)
            }
        }
    }


  fun displayPropertyDetails(asteroid: Asteroid){
    _navigateToDetailData.value = asteroid
  }
  fun displayPropertyDetailsCompleted(){
      _navigateToDetailData.value = null
  }




    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}


