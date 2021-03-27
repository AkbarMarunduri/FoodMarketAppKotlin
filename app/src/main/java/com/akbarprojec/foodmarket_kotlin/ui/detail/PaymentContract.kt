package com.akbarprojec.foodmarket_kotlin.ui.detail

import com.akbarprojec.foodmarket_kotlin.base.BasePresenter
import com.akbarprojec.foodmarket_kotlin.base.BaseView
import com.akbarprojec.foodmarket_kotlin.model.response.checkout.CheckoutResponse
import com.akbarprojec.foodmarket_kotlin.model.response.home.HomeResponse
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse

interface PaymentContract {
    interface View : BaseView {
        fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: android.view.View)
        fun onCheckoutFailed(massage: String)
    }

    interface Presenter : PaymentContract, BasePresenter {
        fun getCeckout(
            foodId: String,
            userId: String,
            quantity: String,
            total: String,
            view: android.view.View
        )
    }
}