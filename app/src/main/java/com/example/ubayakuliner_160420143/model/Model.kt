package com.example.ubayakuliner_160420143.model

import com.google.gson.annotations.SerializedName
data class Food(
    val id:String?,
    var nama:String?,
    var deksripsi:String?,
    var harga:String?,
    @SerializedName("url_gambar")
    var photoUrl:String?
)
data class Review(
    val id:String?,
    var namaUser:String?,
    var bintang:String?,
    var komen:String?,
)
data class Tenant(
    val id:String?,
    @SerializedName("nama_tempat")
    var nama:String?,
    var alamat:String?,
    @SerializedName("jam_operasional")
    var jamOperasional:String?,
    var bintang:String?,
    var foods:ArrayList<Food>,
    var reviews:ArrayList<Review>,
    @SerializedName("gambar_tempat_url")
    var photoUrl:String?
)


