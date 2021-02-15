package com.akbarprojec.foodmarket_kotlin.ui.home.newtaste

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.dummy.HomeVerticalModel
import kotlinx.android.synthetic.main.fragment_home_new_taste.*

class HomeNewTasteFragment : Fragment(),HomeNewTasteAdapter.ItemAdapterCallBack {
    private  var foodlist: ArrayList<HomeVerticalModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_new_taste, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDummy()
        var adapter=HomeNewTasteAdapter(foodlist,this)
        var layoutManeger:RecyclerView.LayoutManager=LinearLayoutManager(activity)
        rvNewTaste.layoutManager=layoutManeger
        rvNewTaste.adapter=adapter

    }
    fun initDummy() {
        foodlist = ArrayList()
        foodlist.add(HomeVerticalModel("Samnyang","",5F,"50000"))
        foodlist.add(HomeVerticalModel("Amsyong","",1F,"100"))
        foodlist.add(HomeVerticalModel("Ragii","",2F,"1000"))
    }

    override fun onClick(v: View, data: HomeVerticalModel) {
        TODO("Not yet implemented")
    }


}