package com.akbarprojec.foodmarket_kotlin.ui.auth.singup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_singup.*
import kotlinx.android.synthetic.main.fragment_singup_address.*

class SingUpAddressFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singup_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        singUpNow.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.accing_singup_succes,null)
            (activity as AuthActivity).toolbarSingUpSucces()
        }
    }

}