package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.util.loadImage
import com.example.ubayakuliner_160420143.viewmodel.DetailTenantViewModel
import kotlinx.android.synthetic.main.fragment_tenant_detail.*
import org.w3c.dom.Text

class TenantDetailFragment : Fragment() {
    private lateinit var viewModel:DetailTenantViewModel
    private lateinit var navController: NavController
    private val foodListAdapter = FoodListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tenant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val tenantId = OptionDetailTenantFragmentArgs.fromBundle(requireArguments()).tenantId
            viewModel = ViewModelProvider(this).get(DetailTenantViewModel::class.java)
            viewModel.refresh(tenantId)

            recViewListFood.layoutManager = LinearLayoutManager(context)
            recViewListFood.adapter = foodListAdapter

            observeViewModel()
            navController = Navigation.findNavController(requireParentFragment().requireView())
        }
    }

    private fun observeViewModel() {
        viewModel.tenantLD.observe(viewLifecycleOwner, Observer {
            foodListAdapter.updateFoodList(it.foods)

            val tenant = viewModel.tenantLD.value
            tenant?.let {
                val txtNamaTenant = view?.findViewById<TextView>(R.id.textViewDetailTenantNama)
                val txtJam = view?.findViewById<TextView>(R.id.textViewDetailTenantJam)
                val txtBintang = view?.findViewById<TextView>(R.id.textViewDetailTenantBintang)
                val imgViewTenant = view?.findViewById<ImageView>(R.id.imageViewDetailTenantPhoto)
                val progressBar = view?.findViewById<ProgressBar>(R.id.progressBarDetailTenantPhoto)
                txtNamaTenant?.text = it.nama
                txtJam?.text = it.jamOperasional
                txtBintang?.text = it.bintang
                if (progressBar != null) {
                    imgViewTenant?.loadImage(it.photoUrl,progressBar)
                }

                val textReview = view?.findViewById<TextView>(R.id.textViewSeeReview)
                val tenantId = it.id.toString()
                textReview?.setOnClickListener {
                    val action = TenantDetailFragmentDirections.actionReviewTenant(tenantId)
                    navController.navigate(action)
                }
            }
        })
    }
}