package com.akbarprojec.foodmarket_kotlin.ui.order

import com.akbarprojec.foodmarket_kotlin.base.BasePresenter
import com.akbarprojec.foodmarket_kotlin.base.BaseView
import com.akbarprojec.foodmarket_kotlin.model.response.home.HomeResponse
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.TransactionResponse

interface OrderContract {
    interface View : BaseView {
        fun onTransactionSuccess (transactionResponse: TransactionResponse)
        fun onTransactionFailed(massage: String)
    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()
    }
}