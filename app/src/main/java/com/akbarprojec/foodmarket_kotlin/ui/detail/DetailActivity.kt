


package com.akbarprojec.foodmarket_kotlin.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.navigation.Navigation
import com.akbarprojec.foodmarket_kotlin.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //data extras dari home fragment dikirim ke argument di nav_detail.xml
        intent.extras?.let {
            val navController=Navigation.findNavController(findViewById(R.id.detailHostFragment))
            val bundlle = Bundle()
            bundlle.putParcelable("data",it.get("data")as Parcelable?)
            navController.setGraph(navController.graph,bundlle)
        }
    }

    fun toolbarPayment() {
        toolbar.visibility=View.VISIBLE
        toolbar.title="Payment"
        toolbar.subtitle="You deverse better meal"
        toolbar.navigationIcon=resources.getDrawable(R.drawable.ic_arrow_back_000,null)
        toolbar.setNavigationOnClickListener{onBackPressed()}
    }

    fun toolbarDetail() {
        toolbar.visibility= View.GONE
    }
}