package com.akbarprojec.foodmarket_kotlin.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akbarprojec.foodmarket_kotlin.R
import kotlinx.android.synthetic.main.fragment_payment_success.*


class PaymentSuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_success, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as DetailActivity).toolbarPayment()

        btnOrderFood.setOnClickListener(){
            requireActivity().finish()
        }

        btnMyOrder.setOnClickListener(){
            requireActivity().finish()
        }

    }


}