package com.example.ubayakuliner_160420143.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.viewmodel.OptionDetailFoodViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.ubayakuliner_160420143.util.loadImage

class OptionDetailFoodFragment : BottomSheetDialogFragment() {
    private lateinit var viewModel: OptionDetailFoodViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_detail_food, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null) {
            val foodId = OptionDetailFoodFragmentArgs.fromBundle(requireArguments()).foodId
            viewModel = ViewModelProvider(this).get(OptionDetailFoodViewModel::class.java)
            viewModel.fetch(foodId)

            observeViewModel()
        }
    }

    fun observeViewModel() {
        viewModel.foodLD.observe(viewLifecycleOwner, Observer {
            val student = viewModel.foodLD.value
            student?.let { it ->
                val textOptionDetailFoodNama =view?.findViewById<TextView>(R.id.TextViewDetailFoodNama)
                val textOptionDetailFoodHarga =view?.findViewById<TextView>(R.id.textViewDetailFoodHarga)
                val textOptionDetailFoodDeskripsi =view?.findViewById<TextView>(R.id.textViewDetailFoodDeksripsi)
                val imageViewDetailFood = view?.findViewById<ImageView>(R.id.imageViewDetailFoodPhoto)
                val progressBar = view?.findViewById<ProgressBar>(R.id.progressBarDetailFood)
                textOptionDetailFoodNama?.text = it.nama
                textOptionDetailFoodHarga?.text = it.harga
                textOptionDetailFoodDeskripsi?.text = it.deksripsi
                if (progressBar != null) {
                    imageViewDetailFood?.loadImage(it.photoUrl,progressBar)
                }
            }
        })
    }
}