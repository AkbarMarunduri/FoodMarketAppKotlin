package com.akbarprojec.foodmarket_kotlin.model.response.transaction


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Food(
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("ingredients")
    val ingredients: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @SerializedName("picturePath")
    val picturePath: String,
    @Expose
    @SerializedName("price")
    val price: Int,
    @SerializedName("rate")
    val rate: Int,
    @Expose
    @SerializedName("types")
    val types: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long
)