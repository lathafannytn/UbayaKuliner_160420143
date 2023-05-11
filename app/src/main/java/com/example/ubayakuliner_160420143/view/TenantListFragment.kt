package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.viewmodel.ListTenantViewModel
import kotlinx.android.synthetic.main.fragment_tenant_list.*

class TenantListFragment : Fragment() {
    private lateinit var viewModel:ListTenantViewModel
    private val tenantListAdapter = TenantListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListTenantViewModel::class.java)
        viewModel.refresh()

        recViewTenant.layoutManager = LinearLayoutManager(context)
        recViewTenant.adapter = tenantListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recViewTenant.visibility = View.GONE
            textErrorTenantList.visibility = View.GONE
            progressBarTenantList.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel() {
        viewModel.tenantsLD.observe(viewLifecycleOwner, Observer {
            tenantListAdapter.updateTenantList(it)
        })

        viewModel.tenantLoadErrorLD.observe(viewLifecycleOwner, Observer {
            val txtError = view?.findViewById<TextView>(R.id.textErrorTenantList)
            if(it == true) {
                txtError?.visibility = View.VISIBLE
            } else {
                txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressBarTenantList)
            val recView = view?.findViewById<RecyclerView>(R.id.recViewTenant)
            if (it){
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            }else{
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }
}