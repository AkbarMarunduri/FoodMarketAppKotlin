package com.akbarprojec.foodmarket_kotlin.ui.home.recomended

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.dummy.HomeVerticalModel
import com.akbarprojec.foodmarket_kotlin.ui.detail.DetailActivity
import com.akbarprojec.foodmarket_kotlin.ui.home.newtaste.HomeNewTasteAdapter
import kotlinx.android.synthetic.main.fragment_recomended_taste.*

class HomeRecomendedFragment : Fragment(), HomeNewTasteAdapter.ItemAdapterCallBack {
    private var foodlist: ArrayList<HomeVerticalModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_recomended_taste, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDummy()
        var adapter = HomeNewTasteAdapter(foodlist, this)
        var layoutManeger: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rvRecomended.layoutManager = layoutManeger
        rvRecomended.adapter = adapter

    }

    fun initDummy() {
        foodlist = ArrayList()
        foodlist.add(HomeVerticalModel("Batu Bata Goreng", "", 5F, "50000"))
        foodlist.add(HomeVerticalModel("Batu Kali", "", 1F, "100"))
        foodlist.add(HomeVerticalModel("Batu Nisyang", "", 2F, "1000"))
    }

    override fun onClick(v: View, data: HomeVerticalModel) {
        val detail = Intent(activity, DetailActivity::class.java)
        startActivity(detail)
    }

}