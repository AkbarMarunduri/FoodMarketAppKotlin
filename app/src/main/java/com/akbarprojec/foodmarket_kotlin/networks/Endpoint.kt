package com.akbarprojec.foodmarket_kotlin.networks

import com.akbarprojec.foodmarket_kotlin.model.response.Wrapper
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Endpoint {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

}