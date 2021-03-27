package com.akbarprojec.foodmarket_kotlin.ui.auth.singup

import android.net.Uri
import android.view.View
import com.akbarprojec.foodmarket_kotlin.model.request.RegisterRequest
import com.akbarprojec.foodmarket_kotlin.networks.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class SignUpPresenter(private val view: SignUpContract.View) /*implements*/ : SignUpContract.Presenter {
    private val aCompositeDisposable: CompositeDisposable?

    init {
        this.aCompositeDisposable = CompositeDisposable()
    }

    override fun submitRegister(register: RegisterRequest, viewParms: View) {
        view.showLLoading()
        val disposable = HttpClient.getInstance().getApi()!!.register(
            register.name,
            register.email,
            register.password,
            register.password_confirmation,
            register.address,
            register.city,
            register.houseNumber,
            register.phoneNumber
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismisLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onRegisterSucces(it1, viewParms) }
                } else {
                    view.onRegisterFailled(it.meta?.message.toString())
                }
            }, {
                view.dismisLoading()
                view.onRegisterFailled(it.message.toString())
            })
        aCompositeDisposable!!.add(disposable)
    }

    override fun subminPhotoRegister(filePath: Uri, viewParms: View) {
        view.showLLoading()

        var profileImageFile = File(filePath.path)
        var profirImageRequestBody = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), profileImageFile)
        var profilleImageParms = MultipartBody.Part.createFormData("file", profileImageFile.name, profirImageRequestBody)

        val disposable = HttpClient.getInstance().getApi()!!.registerPhoto(profilleImageParms)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismisLoading()
                if (it.meta?.status.equals("success", true)) {
                    it.data?.let { it1 -> view.onRegisterPhotoSucces(viewParms) }
                } else {
                    view.onRegisterFailled(it.meta?.message.toString())
                }
            }, {
                view.dismisLoading()
                view.onRegisterFailled(it.message.toString())
            })
        aCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
        aCompositeDisposable!!.clear()
    }

}