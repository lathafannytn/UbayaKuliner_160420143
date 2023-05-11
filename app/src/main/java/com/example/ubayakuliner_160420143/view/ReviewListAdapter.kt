package com.example.ubayakuliner_160420143.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ubayakuliner_160420143.R
import com.example.ubayakuliner_160420143.model.Review
import kotlinx.android.synthetic.main.review_card.view.*

class ReviewListAdapter(val reviewList:ArrayList<Review>):
    RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view:View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_card,parent,false)

        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        with(holder.view){
            textViewNamaUser.text = review.namaUser
            ratingBar.rating = review.bintang?.toFloat() ?: 5.0.toFloat()
            ratingBar.numStars = 5

            cardViewReviewUser.setOnClickListener {
                val reviewId = review.id.toString()
                val action = ReviewTenantFragmentDirections.actionOptionReview(reviewId)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int = reviewList.size

    fun updateReviewList(newReviewList: ArrayList<Review>){
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }
}