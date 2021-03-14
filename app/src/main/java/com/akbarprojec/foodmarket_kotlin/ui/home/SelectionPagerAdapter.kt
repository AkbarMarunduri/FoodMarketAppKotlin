package com.akbarprojec.foodmarket_kotlin.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbarprojec.foodmarket_kotlin.model.response.home.Data
import com.akbarprojec.foodmarket_kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.popular.HomePopularFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.recomended.HomeRecomendedFragment

class SelectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var newStateList: ArrayList<Data>? = ArrayList()
    var popularLList: ArrayList<Data>? = ArrayList()
    var recomendeList: ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "New Taste"
            1 -> "Popular"
            2 -> "Recomended"
            else -> ""
        }
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data",newStateList)
                fragment.arguments=bundle
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data",popularLList)
                fragment.arguments=bundle
                return fragment
            }
            2 -> {
                fragment = HomeRecomendedFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data",recomendeList)
                fragment.arguments=bundle
                return fragment
            }
            else -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data",newStateList)
                fragment.arguments=bundle
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    fun setData(
        newListPams: ArrayList<Data>?,
        popularParms: ArrayList<Data>,
        recomendenParms: ArrayList<Data>
    ) {
        newStateList = newListPams
        popularLList = popularParms
        recomendeList = recomendenParms
    }

}