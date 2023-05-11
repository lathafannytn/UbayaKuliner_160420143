package com.example.ubayakuliner_160420143.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.model.Food
import com.example.ubayakuliner_160420143.model.Tenant
import com.example.ubayakuliner_160420143.util.loadImage
import kotlinx.android.synthetic.main.food_card.view.*

class FoodListAdapter(val foodList:ArrayList<Food>):
    RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>(){
    class FoodViewHolder(var view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_card,parent,false)

        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        with(holder.view){
            textViewFoodNama.text = food.nama
            textViewFoodHarga.text = food.harga

            imageViewFood.loadImage(food.photoUrl, progressBarFoodCard)
            buttonLihatFood.setOnClickListener {
                val foodId = food.id.toString()
                val action = TenantDetailFragmentDirections.actionOptionDetailFood(foodId)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = foodList.size

    fun updateFoodList(newFoodList: ArrayList<Food>){
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}