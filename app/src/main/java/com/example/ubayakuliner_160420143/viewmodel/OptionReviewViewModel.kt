package com.example.ubayakuliner_160420143.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ubayakuliner_160420143.model.Food
import com.example.ubayakuliner_160420143.model.Review
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OptionReviewViewModel(application: Application): AndroidViewModel(application) {
    val reviewLD = MutableLiveData<Review>()
    val TAG = "VolleyTag"
    private var queue: RequestQueue? = null
    fun fetch(reviewId:String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://fanny-tn.000webhostapp.com/foods.php?idReview=$reviewId"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Review>() {}.type
                val result = Gson().fromJson<Review>(it, sType)
                reviewLD.value = result
            },
            {
                Log.d("ShowVoley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
}