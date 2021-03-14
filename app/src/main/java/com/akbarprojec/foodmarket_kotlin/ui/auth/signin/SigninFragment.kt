package com.akbarprojec.foodmarket_kotlin.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.akbarprojec.foodmarket_kotlin.FoodMarket
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.login.LoginResponse
import com.akbarprojec.foodmarket_kotlin.ui.MainActivity
import com.akbarprojec.foodmarket_kotlin.ui.auth.AuthActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_singin.*


class SigninFragment : Fragment(), SignContract.View {
    lateinit var presenter: SigninPresenter
    var progresssDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_singin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SigninPresenter(this)

        //periksa apakah token sudah ada / belum
//        if (!FoodMarket.getApp().getToken().isNullOrEmpty()) {
//            val home = Intent(activity, MainActivity::class.java)
//            startActivity(home)
//            activity?.finish()
//        }

        initView()
        initDummy()

        btnSingUp.setOnClickListener {
            val singup = Intent(activity, AuthActivity::class.java)
            singup.putExtra("page_request", 2)
            startActivity(singup)
        }

        btSingIn.setOnClickListener {
            var email=edEmail.text.toString()
            var password=edPassword.text.toString()

            if (email.isNullOrEmpty()) {
                edEmail.error = "Silahkan masukkan email anda"
                edEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                edPassword.error = "Silahkan masukkan password anda"
                edPassword.requestFocus()
            } else {
                presenter.submitLogin(email, password)
            }

        }
    }

    fun initDummy() {
        edEmail.setText("Narto.kim@blackpink.co")
        edPassword.setText("12345678")
    }

    override fun onLogingSuccess(loginresponse: LoginResponse) {

        FoodMarket.getApp().setToken(loginresponse.access_token)
        val gson = Gson()
        val json=gson.toJson(loginresponse.user)
        FoodMarket.getApp().setUSer(json)


        val home = Intent(activity, MainActivity::class.java)
        startActivity(home)
        activity?.finish()
    }

    override fun onLoginFailed(massage: String) {
        Toast.makeText(activity, massage, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        progresssDialog = Dialog(requireContext())
        val dialogLLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progresssDialog?.let {
            it.setContentView(dialogLLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

    }

    override fun showLLoading() {
        progresssDialog?.show()
    }

    override fun dismisLoading() {
        progresssDialog?.dismiss()
    }

}