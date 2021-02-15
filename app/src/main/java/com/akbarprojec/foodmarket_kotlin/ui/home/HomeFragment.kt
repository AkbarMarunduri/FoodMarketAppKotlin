package com.akbarprojec.foodmarket_kotlin.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.dummy.HomeModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallBack {

    private var foodlist: ArrayList<HomeModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummy()
        var adapter = HomeAdapter(foodlist, this)
        val layoutManeger: RecyclerView.LayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvList.layoutManager = layoutManeger
        rvList.adapter = adapter

        val selectionPagerAdapter = SelectionPagerAdapter(childFragmentManager)
        viewPager.adapter = selectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    fun initDummy() {
        foodlist = ArrayList()
        foodlist.add(HomeModel("Makanan Hallal", "", 5F))
        foodlist.add(HomeModel("Makanan Haram", "", 1F))
        foodlist.add(HomeModel("Makanan Subhat", "", 2F))
    }

    override fun onClick(v: View, data: HomeModel) {
        Toast.makeText(context, "In Progres ", Toast.LENGTH_LONG).show()
    }
}