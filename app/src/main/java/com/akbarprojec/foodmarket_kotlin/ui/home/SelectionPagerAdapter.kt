package com.akbarprojec.foodmarket_kotlin.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbarprojec.foodmarket_kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.popular.HomePopularFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.recomended.HomeRecomendedFragment

class SelectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

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
                return fragment
            }
            1 -> {
                fragment = HomePopularFragment()
                return fragment
            }
            2 -> {
                fragment = HomeRecomendedFragment()
                return fragment
            }
            else -> {
                fragment = HomeNewTasteFragment()
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

}