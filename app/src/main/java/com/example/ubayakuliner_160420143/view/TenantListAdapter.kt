package com.example.ubayakuliner_160420143.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.model.Tenant
import com.example.ubayakuliner_160420143.util.loadImage
import kotlinx.android.synthetic.main.tenant_card.view.*

class TenantListAdapter(val tenantList:ArrayList<Tenant>):
    RecyclerView.Adapter<TenantListAdapter.TenantViewHolder>() {
    class TenantViewHolder(var view:View) :RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TenantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.tenant_card,parent,false)

        return TenantViewHolder(view)
    }

    override fun onBindViewHolder(holder: TenantViewHolder, position: Int) {
        val tenant = tenantList[position]
        with (holder.view){
//            Log.d("namatenant",tenant.nama.toString())
            textNamaTenant.text = tenant.nama
            textJamTenant.text = tenant.jamOperasional
//
            imageViewTenant.loadImage(tenant.photoUrl, progressBarTenantPhoto)
            cardViewTenantList.setOnClickListener {
                val idTenant = tenant.id.toString()
                val action = TenantListFragmentDirections.actionOptionTenantFragment(idTenant)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = tenantList.size

    fun updateTenantList(newTenantList: ArrayList<Tenant>){
        tenantList.clear()
        tenantList.addAll(newTenantList)
        notifyDataSetChanged()
    }
}