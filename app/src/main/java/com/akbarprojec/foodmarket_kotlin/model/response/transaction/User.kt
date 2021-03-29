package com.akbarprojec.foodmarket_kotlin.model.response.transaction


import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @Expose
    @SerializedName("address")
    val address: String,
    @Expose
    @SerializedName("city")
    val city: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: Long,
    @Expose
    @SerializedName("current_team_id")
    val currentTeamId: String?,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String?,
    @Expose
    @SerializedName("houseNumber")
    val houseNumber: String,
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @Expose
    @SerializedName("profile_photo_path")
    val profilePhotoPath: String,
    @Expose
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String,
    @Expose
    @SerializedName("roles")
    val roles: String,
    @Expose
    @SerializedName("updated_at")
    val updatedAt: Long?
):Parcelable