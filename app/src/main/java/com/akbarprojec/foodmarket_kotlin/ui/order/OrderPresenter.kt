package com.akbarprojec.foodmarket_kotlin.ui.order

import com.akbarprojec.foodmarket_kotlin.networks.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OrderPresenter(private val view: OrderContract.View) : OrderContract.Presenter {
    private val aCompositeDisposable: CompositeDisposable?

    init {
        this.aCompositeDisposable = CompositeDisposable()
    }

    override fun getTransaction() {
        view.showLLoading()
        val disposable = HttpClient.getInstance().getApi()!!.transaction()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismisLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onTransactionSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onTransactionFailed(it1) }
                    }
                },
                {
                    view.dismisLoading()
                    view.onTransactionFailed(it.message.toString())
                }
            )
        aCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        aCompositeDisposable!!.clear()
    }

}