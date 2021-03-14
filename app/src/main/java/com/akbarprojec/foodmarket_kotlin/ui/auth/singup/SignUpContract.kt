package com.akbarprojec.foodmarket_kotlin.ui.auth.singup

import android.net.Uri
import com.akbarprojec.foodmarket_kotlin.base.BasePresenter
import com.akbarprojec.foodmarket_kotlin.base.BaseView
import com.akbarprojec.foodmarket_kotlin.model.request.RegisterRequest
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse

interface  SignUpContract {
    interface View : BaseView {
        fun onRegisterSucces(loginresponse: LoginResponse, view: android.view.View)
        fun onRegisterPhotoSucces(view: android.view.View)
        fun onRegisterFailled(message: String)
    }

    interface Presenter : SignUpContract, BasePresenter {
        fun submitRegister(register: RegisterRequest, view: android.view.View)
        fun subminPhotoRegister(fiePath: Uri, view: android.view.View)
    }
}