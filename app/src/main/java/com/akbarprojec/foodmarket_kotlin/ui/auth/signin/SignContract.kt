package com.akbarprojec.foodmarket_kotlin.ui.auth.signin

import com.akbarprojec.foodmarket_kotlin.base.BasePresenter
import com.akbarprojec.foodmarket_kotlin.base.BaseView
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse

interface SignContract {
    interface View : BaseView {
        fun onLogingSuccess(loginresponse: LoginResponse)
        fun onLoginFailed(massage: String)
    }

    interface Presenter : SignContract, BasePresenter {
        fun submitLogin(email:String,passwoed:String)
    }
}