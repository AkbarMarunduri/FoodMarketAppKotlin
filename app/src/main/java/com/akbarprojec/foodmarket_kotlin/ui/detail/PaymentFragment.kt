package com.akbarprojec.foodmarket_kotlin.ui.detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.UserHandle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.akbarprojec.foodmarket_kotlin.FoodMarket
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.checkout.CheckoutResponse
import com.akbarprojec.foodmarket_kotlin.model.response.home.Data
import com.akbarprojec.foodmarket_kotlin.model.response.login.User
import com.akbarprojec.foodmarket_kotlin.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_payment.*


class PaymentFragment : Fragment(),PaymentContract.View {
    var progresdialog:Dialog?=null
    lateinit var presenter:PaymentPresenter
    var total:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarPayment()

        var data= arguments?.getParcelable<Data>("data")

        initView(data)
        initView()
        presenter=PaymentPresenter(this)
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

    private fun initView(data: Data?) {
        tvTitle.text=data?.name
        tvPrice.formatPrice(data?.price.toString())

        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(ivPoster)

        tvNameItem.text=data?.name
        tvItemPrice.formatPrice(data?.price.toString())

        if (!data?.price.toString().isNullOrEmpty()) {
            var totalTax = data?.price?.div(10)
            tvTaxPrice.formatPrice(totalTax.toString())
            total = data?.price!! + totalTax!! + 5000
            tvTotalPice.formatPrice(total.toString())
        } else {
            tvPrice.text="IDR. 0"
            tvItemPrice.text="IDR. 0"
            tvTaxPrice.text="IDR. 0"
            tvTotalPice.text="IDR. 0"
        }

        var user=FoodMarket.getApp().getUser()
        var userResponse=Gson().fromJson(user,User::class.java)

        userResponse?.let {
            tvName.text=it.name
            tvAddress.text=it.address
            tvPhone.text=it.phoneNumber
            tvCity.text=it.city
        }

        btnCheckoutNow.setOnClickListener {
            presenter.getCeckout(data?.id.toString(),userResponse.id.toString(),"1",total.toString(),it)

        }
    }

    override fun onCheckoutSuccess(checkoutResponse: CheckoutResponse, view: View) {
        val i=Intent(Intent.ACTION_VIEW)
        i.data=Uri.parse(checkoutResponse.paymentUrl)
        startActivity(i)

        Navigation.findNavController(view).navigate(R.id.action_payment_success)

    }

    override fun onCheckoutFailed(massage: String) {
       Toast.makeText(context,massage,Toast.LENGTH_SHORT).show()
    }

    override fun showLLoading() {
        progresdialog?.show()
    }

    override fun dismisLoading() {
        progresdialog?.dismiss()
    }


}