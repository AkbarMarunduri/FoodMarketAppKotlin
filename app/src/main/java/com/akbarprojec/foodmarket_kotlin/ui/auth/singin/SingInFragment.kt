package com.akbarprojec.foodmarket_kotlin.ui.auth.singin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.ui.MainActivity
import com.akbarprojec.foodmarket_kotlin.ui.auth.AuthActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_singin.*


class SingInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singin, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnSingUp.setOnClickListener {
            val singup = Intent(activity, AuthActivity::class.java)
            singup.putExtra("page_request",2)
            startActivity(singup)
        }

        btSingIn.setOnClickListener {
            val home=Intent(activity,MainActivity::class.java)
            startActivity(home)
            activity?.finish()
        }
    }

}