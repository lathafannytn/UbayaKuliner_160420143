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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.util.loadImage
import com.example.ubayakuliner_160420143.viewmodel.FoodCategoryViewModel
import com.example.ubayakuliner_160420143.viewmodel.ListTenantViewModel
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {
    private lateinit var viewModel:FoodCategoryViewModel
    private val foodListAdapter = FoodListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var kategori = "breakfast"
        viewModel = ViewModelProvider(this).get(FoodCategoryViewModel::class.java)
        viewModel.refresh(kategori)

        recViewKategoriMakanan.layoutManager = LinearLayoutManager(context)
        recViewKategoriMakanan.adapter = foodListAdapter

        observeViewModel()
        textViewNamaKategori.text = "Kategori Makanan : $kategori"
        imageViewBreakfast.setOnClickListener {
            kategori = "breakfast"
            viewModel.refresh(kategori)
            observeViewModel()
            textViewNamaKategori.text = "Kategori Makanan : $kategori"
        }

        imageViewLunch.setOnClickListener {
            kategori = "lunch"
            viewModel.refresh(kategori)
            observeViewModel()
            textViewNamaKategori.text = "Kategori Makanan : $kategori"
        }

        imageViewDinner.setOnClickListener {
            kategori = "dinner"
            viewModel.refresh(kategori)
            observeViewModel()
            textViewNamaKategori.text = "Kategori Makanan : $kategori"
        }
    }

    private fun observeViewModel() {
        viewModel.foodsLD.observe(viewLifecycleOwner, Observer {
            foodListAdapter.updateFoodList(it)
        })
    }
}