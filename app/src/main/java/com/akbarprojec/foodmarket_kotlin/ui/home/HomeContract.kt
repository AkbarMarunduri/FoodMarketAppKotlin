package com.akbarprojec.foodmarket_kotlin.ui.home

import com.akbarprojec.foodmarket_kotlin.base.BasePresenter
import com.akbarprojec.foodmarket_kotlin.base.BaseView
import com.akbarprojec.foodmarket_kotlin.model.response.home.HomeResponse
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse

interface HomeContract {
    interface View : BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(massage: String)
    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
    }
}