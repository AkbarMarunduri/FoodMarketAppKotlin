package com.akbarprojec.foodmarket_kotlin.ui.auth.singup

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_singup.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class SingUpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        continueButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_singup_address, null)
            (activity as AuthActivity).toolbarSingUpAddress()
        }

    }
}