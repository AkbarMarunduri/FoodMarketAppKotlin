package com.akbarprojec.foodmarket_kotlin.ui.home

import com.akbarprojec.foodmarket_kotlin.networks.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {
    private val aCompositeDisposable: CompositeDisposable?

    init {
        this.aCompositeDisposable = CompositeDisposable()
    }

    override fun getHome() {
        view.showLLoading()
        val disposable = HttpClient.getInstance().getApi()!!.home()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismisLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onHomeSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onHomeFailed(it1) }
                    }
                },
                {
                    view.dismisLoading()
                    view.onHomeFailed(it.message.toString())
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