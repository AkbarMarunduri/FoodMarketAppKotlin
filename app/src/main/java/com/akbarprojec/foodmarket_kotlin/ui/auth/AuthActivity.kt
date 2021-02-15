package com.akbarprojec.foodmarket_kotlin.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import com.akbarprojec.foodmarket_kotlin.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val pageRequest = intent.getIntExtra("page_request", 0)

        if (pageRequest == 2) {

            //load fragment main sebagai default
            toolbarSingUp()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.SingUpFragment, true)
                .build()

            Navigation.findNavController(findViewById(R.id.navHostFragment))
                .navigate(R.id.action_singup, null, navOptions)

        }
    }

    fun toolbarSingUp() {
        toolbar.title = "Sing Up"
        toolbar.subtitle = "Register and eat"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSingUpAddress() {
        toolbar.title = "Address"
        toolbar.subtitle = "make sure it's valid"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun toolbarSingUpSucces() {
        toolbar.visibility = View.GONE
    }
}