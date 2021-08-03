package com.kasai.speed_whether.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kasai.speed_whether.model.WeatherInfo
import com.kasai.speed_whether.repository.WeatherInfoRepository
import kotlinx.coroutines.launch

class WeatherInfoViewModel : ViewModel() {
    private val repository = WeatherInfoRepository.instance

    //監視対象のLiveData
    var weatherInfoLiveData: MutableLiveData<WeatherInfo> = MutableLiveData()

    //ViewModel初期化時にロード
    init {
        loadWeatherInfo()
    }

    private fun loadWeatherInfo() {
        //viewModelScope->ViewModel.onCleared() のタイミングでキャンセルされる CoroutineScope
        viewModelScope.launch {
            try {
                // 実行時は、appIDを自分のやつに書き換えする
                val request = repository.getWeatherInfo("35.68", "139.77", "minutely", "appid")
                Log.d("AAAAAAAAAAAAAAAA1", "AAAAAAAAAAAAAAAAAAAAA")
                weatherInfoLiveData.postValue(request.body())
                if (request.isSuccessful) {
                    //データを取得したら、LiveDataを更新
                    weatherInfoLiveData.postValue(request.body())
                    Log.d("AAAAAAAAAAAAAAAAAAAAA2", "AAAAAAAAAAAAAAAAAAAAA")
                }
            } catch (e: Exception) {
                e.stackTrace

                Log.d("AAAAAAAAAAAAAAAA3", "AAAAAAAAAAAAAAAAAAAAA")
                e.printStackTrace()
            }
        }
    }

    private fun A() {
// Use fields to define the data types to return.
        val placeFields: List<Place.Field> = listOf(Place.Field.NAME)

// Use the builder to create a FindCurrentPlaceRequest.
        val request: FindCurrentPlaceRequest = FindCurrentPlaceRequest.newInstance(placeFields)

// Call findCurrentPlace and handle the response (first check that the user has granted permission).
        if (ContextCompat.checkSelfPermission(this, permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {

            val placeResponse = placesClient.findCurrentPlace(request)
            placeResponse.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val response = task.result
                    for (placeLikelihood: PlaceLikelihood in response?.placeLikelihoods ?: emptyList()) {
                        Log.i(
                            TAG,
                            "Place '${placeLikelihood.place.name}' has likelihood: ${placeLikelihood.likelihood}"
                        )
                    }
                } else {
                    val exception = task.exception
                    if (exception is ApiException) {
                        Log.e(TAG, "Place not found: ${exception.statusCode}")
                    }
                }
            }
        } else {
            // A local method to request required permissions;
            // See https://developer.android.com/training/permissions/requesting
            getLocationPermission()
        }

    }
}