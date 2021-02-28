package com.akbarprojec.foodmarket_kotlin.ui.auth.signin

import com.akbarprojec.foodmarket_kotlin.networks.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SiginPresenter(private val view: SignContract.View) : SignContract.Presenter {
    private val aCompositeDisposable: CompositeDisposable?

    init {
        this.aCompositeDisposable = CompositeDisposable()
    }

    override fun submitLogin(email: String, password: String) {
        view.showLLoading()
        val disposable = HttpClient.getInstance().getApi()!!.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismisLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onLogingSuccess(it1) }
                } else {
                    it.meta?.message?.let { it1 -> view.onLoginFailed(it1) }
                }
            }, {
                view.dismisLoading()
                view.onLoginFailed(it.message.toString())
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