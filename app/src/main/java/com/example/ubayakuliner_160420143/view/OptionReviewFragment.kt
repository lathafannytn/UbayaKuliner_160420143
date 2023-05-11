package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.viewmodel.OptionReviewViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionReviewFragment : BottomSheetDialogFragment() {
    private lateinit var viewModel: OptionReviewViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!= null){
            val reviewId = OptionReviewFragmentArgs.fromBundle(requireArguments()).reviewId
            viewModel = ViewModelProvider(this).get(OptionReviewViewModel::class.java)
            viewModel.fetch(reviewId)
            observeViewModel()
        }
    }

    fun observeViewModel() {
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            val student = viewModel.reviewLD.value
            student?.let { it ->
                val textReviewNama =view?.findViewById<TextView>(R.id.textViewOptionReviewNama)
                val textReviewKomen =view?.findViewById<TextView>(R.id.textViewOptionReviewKomen)
                textReviewNama?.text = it.namaUser
                textReviewKomen?.text = it.komen
            }
        })
    }
}