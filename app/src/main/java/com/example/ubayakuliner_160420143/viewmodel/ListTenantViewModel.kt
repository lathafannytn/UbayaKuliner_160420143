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

class ListTenantViewModel(application: Application): AndroidViewModel(application) {
    val tenantsLD = MutableLiveData<ArrayList<Tenant>>()
    val tenantLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "VolleyTag"
    private var queue: RequestQueue? = null
    fun refresh(){
        loadingLD.value = true
        tenantLoadErrorLD.value = false

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://fanny-tn.000webhostapp.com/foods.php"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Tenant>>() {}.type
                val result = Gson().fromJson<ArrayList<Tenant>>(it, sType)
                tenantsLD.value = result
                loadingLD.value = false
                Log.d("showVolley",it)
                Log.d("show",sType.toString())
                Log.d("show",result.toString())
            },
            {
                Log.d("ShowVoley",it.toString())
                tenantLoadErrorLD.value = true
                loadingLD.value = true
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