package com.akbarprojec.foodmarket_kotlin.ui.order

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbarprojec.foodmarket_kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.popular.HomePopularFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.recomended.HomeRecomendedFragment
import com.akbarprojec.foodmarket_kotlin.ui.order.inprogress.InprogresFragment
import com.akbarprojec.foodmarket_kotlin.ui.order.pastorder.PastorderFragment
import com.akbarprojec.foodmarket_kotlin.ui.profile.account.ProfileAccountFragment
import com.akbarprojec.foodmarket_kotlin.ui.profile.foodmarket.ProfileFoodmarketFragment

class OrderSelectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "In Progress"
            1 -> "Past Order"
            else -> ""
        }
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = InprogresFragment()
                return fragment
            }
            1 -> {
                fragment = PastorderFragment()
                return fragment
            }
            else -> {
                fragment = InprogresFragment()
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

}