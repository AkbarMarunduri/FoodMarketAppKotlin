package com.akbarprojec.foodmarket_kotlin.ui.profile.foodmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.dummy.ProfileMenuModel
import com.akbarprojec.foodmarket_kotlin.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_account.*

class ProfileFoodmarketFragment : Fragment(),ProfileMenuAdapter.ItemAdapterCallBack {
    private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_foodmarket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDataDummy()
        var adaptor = ProfileMenuAdapter(menuArrayList, this)
        var layotManeger: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rvList.layoutManager=layotManeger
        rvList.adapter=adaptor

    }

    fun initDataDummy() {
        menuArrayList = ArrayList()
        menuArrayList.add(ProfileMenuModel("Rate Apps"))
        menuArrayList.add(ProfileMenuModel("Help Center"))
        menuArrayList.add(ProfileMenuModel("Privacy & Polish"))
        menuArrayList.add(ProfileMenuModel("Term & Condition"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context, "Ini menu yang kamu Click ${data.title}", Toast.LENGTH_LONG).show()
    }

}