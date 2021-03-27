package com.akbarprojec.foodmarket_kotlin.ui.detail

import android.view.View
import com.akbarprojec.foodmarket_kotlin.networks.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter(private val view: PaymentContract.View) : PaymentContract.Presenter {
    private val aCompositeDisposable: CompositeDisposable?

    init {
        this.aCompositeDisposable = CompositeDisposable()
    }

    override fun getCeckout(foodId: String, userId: String, quantity: String,total:String, viewParam: View) {
        view.showLLoading()
        val disposable = HttpClient.getInstance().getApi()!!.checkout(
            foodId,userId,quantity,total,"DELIVERED"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismisLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onCheckoutSuccess(it1,viewParam) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onCheckoutFailed(it1) }
                    }
                },
                {
                    view.dismisLoading()
                    view.onCheckoutFailed(it.message.toString())
                }
            )
        aCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun unSubscribe() {
        TODO("Not yet implemented")
    }


}