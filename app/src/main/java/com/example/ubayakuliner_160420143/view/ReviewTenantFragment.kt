package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.util.loadImage
import com.example.ubayakuliner_160420143.viewmodel.DetailTenantViewModel
import kotlinx.android.synthetic.main.fragment_review_tenant.*
import kotlinx.android.synthetic.main.fragment_tenant_detail.*

class ReviewTenantFragment : Fragment() {
    private lateinit var viewModel:DetailTenantViewModel
    private val reviewListAdapter = ReviewListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_tenant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val tenantId = OptionDetailTenantFragmentArgs.fromBundle(requireArguments()).tenantId
            viewModel = ViewModelProvider(this).get(DetailTenantViewModel::class.java)
            viewModel.refresh(tenantId)

            recViewReview.layoutManager = LinearLayoutManager(context)
            recViewReview.adapter = reviewListAdapter

            observeViewModel()
        }
    }
    private fun observeViewModel() {
        viewModel.tenantLD.observe(viewLifecycleOwner, Observer {
            reviewListAdapter.updateReviewList(it.reviews)

            val tenant = viewModel.tenantLD.value
            tenant?.let {
                val txtNamaTenant = view?.findViewById<TextView>(R.id.textViewReviewNamaTenant)
                val txtAlamatTenant = view?.findViewById<TextView>(R.id.textViewReviewAlamat)
                val txtJam = view?.findViewById<TextView>(R.id.textViewReviewJam)
                val txtBintang = view?.findViewById<RatingBar>(R.id.ratingBarTenant)
                txtNamaTenant?.text = it.nama
                txtAlamatTenant?.text = it.alamat
                txtJam?.text = it.jamOperasional
                txtBintang?.rating = it.bintang?.toFloat()!!
            }
        })
    }
}