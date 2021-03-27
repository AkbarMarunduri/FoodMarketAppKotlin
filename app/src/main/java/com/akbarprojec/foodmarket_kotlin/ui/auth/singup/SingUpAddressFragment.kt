package com.akbarprojec.foodmarket_kotlin.ui.auth.singup

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.akbarprojec.foodmarket_kotlin.FoodMarket
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.request.RegisterRequest
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse
import com.akbarprojec.foodmarket_kotlin.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_singup.*
import kotlinx.android.synthetic.main.fragment_singup_address.*

class SingUpAddressFragment : Fragment(), SignUpContract.View {
    private lateinit var data: RegisterRequest
    lateinit var presenter: SignUpPresenter
    var progresdialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = SignUpPresenter(this)
        data = arguments?.getParcelable<RegisterRequest>("data")!!

        initDummy()
        initListener()
        initView()
    }

    fun initListener() {

        singUpNow.setOnClickListener {
            var phone = etPhone.text.toString()
            var address = etAddress.text.toString()
            var houseNumber = etHouseNumber.text.toString()
            var city = etCity.text.toString()

            data.let { it ->
                it.address = address
                it.phoneNumber = phone
                it.houseNumber = houseNumber
                it.city = city
            }

            if (phone.isNullOrEmpty()) {
                etPhone.error = "Masukkan nomor hp"
                etPhone.requestFocus()
            } else if (address.isNullOrEmpty()) {
                etAddress.error = "Masukkan alamat"
                etAddress.requestFocus()
            } else if (houseNumber.isNullOrEmpty()) {
                etHouseNumber.error = "Masukkan nomor rumah"
                etHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()) {
                etCity.error = "Masukkan kota"
                etCity.requestFocus()
            } else {
                presenter.submitRegister(data, it)
               // presenter.subminPhotoRegister(data.filePath!!, it)
            }

        }
    }

    private fun initView() {
        progresdialog = Dialog(requireContext())
        val dialogLLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progresdialog?.let {
            it.setContentView(dialogLLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    fun initDummy() {
        etPhone.setText("12312431421")
        etAddress.setText("Jaan menuju surga")
        etHouseNumber.setText("07")
        etCity.setText("Firdaus")
    }

    override fun onRegisterSucces(loginresponse: LoginResponse, view: View) {
        //simpan token ke sharepreference FootMarked
        FoodMarket.getApp().setToken(loginresponse.access_token)
        val gson = Gson()
        //set response user dari Gson ke json
        val json = gson.toJson(loginresponse.user)
        FoodMarket.getApp().setUSer(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_singup_succes, null)
            (activity as AuthActivity).toolbarSingUpSucces()
        } else {
            presenter.subminPhotoRegister(data.filePath!!, view)

        }
    }

    override fun onRegisterPhotoSucces(view: View) {
        Navigation.findNavController(view)
            .navigate(R.id.action_singup_succes, null)
        (activity as AuthActivity).toolbarSingUpSucces()
    }

    override fun onRegisterFailled(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLLoading() {
        progresdialog?.show()
    }

    override fun dismisLoading() {
        progresdialog?.dismiss()
    }
}