package com.akbarprojec.foodmarket_kotlin.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbarprojec.foodmarket_kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.popular.HomePopularFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.recomended.HomeRecomendedFragment
import com.akbarprojec.foodmarket_kotlin.ui.profile.account.ProfileAccountFragment
import com.akbarprojec.foodmarket_kotlin.ui.profile.foodmarket.ProfileFoodmarketFragment

class SelectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "Food Market"
            else -> ""
        }
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
            1 -> {
                fragment = ProfileFoodmarketFragment()
                return fragment
            }
            else -> {
                fragment = ProfileAccountFragment()
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

}