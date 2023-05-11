package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.viewmodel.OptionDetailTenantViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_option_detail_tenant.*

class OptionDetailTenantFragment : BottomSheetDialogFragment() {
    private lateinit var viewModel: OptionDetailTenantViewModel
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_detail_tenant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val tenantId = OptionDetailTenantFragmentArgs.fromBundle(requireArguments()).tenantId
            viewModel = ViewModelProvider(this).get(OptionDetailTenantViewModel::class.java)
            viewModel.fetch(tenantId)

            observeViewModel()
            navController = Navigation.findNavController(requireParentFragment().requireView())
        }
    }

    fun observeViewModel() {
        viewModel.tenantLD.observe(viewLifecycleOwner, Observer {
            val student = viewModel.tenantLD.value
            student?.let { it ->
                val textOptionDetailTenantNama =view?.findViewById<TextView>(R.id.textOptionDetailTenantNama)
                val textOptionDetailTenantAlamat =view?.findViewById<TextView>(R.id.textOptionDetailTenantAlamat)
                val textOptionDetailTenantJam =view?.findViewById<TextView>(R.id.textOptionDetailTenantJam)
                val btnLihatMenu = view?.findViewById<Button>(R.id.buttonLihatMenu)
                textOptionDetailTenantNama?.text = it.nama
                textOptionDetailTenantAlamat?.text = it.alamat
                textOptionDetailTenantJam?.text = it.jamOperasional
                val tenantId = it.id.toString()
                btnLihatMenu?.setOnClickListener {
                    val action = OptionDetailTenantFragmentDirections.actionDetailTenantFragment(tenantId)
                    navController.navigate(action)
                }
            }
        })
    }
}