package com.akbarprojec.foodmarket_kotlin.ui.detail

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.akbarprojec.foodmarket_kotlin.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

    }

    fun toolbarPayment() {
        toolbar.visibility=View.VISIBLE
        toolbar.title="Paymebt"
        toolbar.subtitle="You deverse better meal"
        toolbar.navigationIcon=resources.getDrawable(R.drawable.ic_arrow_back_000,null)
        toolbar.setNavigationOnClickListener{onBackPressed()}
    }

    fun toolbarDetail() {
        toolbar.visibility= View.GONE
    }
}