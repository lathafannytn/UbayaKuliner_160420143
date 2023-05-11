package com.example.ubayakuliner_160420143.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ubayakuliner_160420143.model.Tenant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OptionDetailTenantViewModel(application: Application): AndroidViewModel(application) {
    val tenantLD = MutableLiveData<Tenant>()
    val TAG = "VolleyTag"
    private var queue: RequestQueue? = null
    fun fetch(tenantId:String) {
        Log.d("idtenant",tenantId)
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://fanny-tn.000webhostapp.com/foods.php?idTenant=$tenantId"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Tenant>() {}.type
                val result = Gson().fromJson<Tenant>(it, sType)
                Log.d("resultoptiondetailtenant",result.toString())
                tenantLD.value = result
            },
            {
                Log.d("ShowVoley",it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}