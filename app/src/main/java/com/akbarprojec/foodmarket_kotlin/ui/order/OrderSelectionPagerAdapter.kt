package com.akbarprojec.foodmarket_kotlin.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.Data
import com.akbarprojec.foodmarket_kotlin.ui.home.newtaste.HomeNewTasteFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.popular.HomePopularFragment
import com.akbarprojec.foodmarket_kotlin.ui.home.recomended.HomeRecomendedFragment
import com.akbarprojec.foodmarket_kotlin.ui.order.inprogress.InprogresFragment
import com.akbarprojec.foodmarket_kotlin.ui.order.pastorder.PastorderFragment
import com.akbarprojec.foodmarket_kotlin.ui.profile.account.ProfileAccountFragment
import com.akbarprojec.foodmarket_kotlin.ui.profile.foodmarket.ProfileFoodmarketFragment

class OrderSelectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var inProgressList:ArrayList<Data>?= ArrayList()
    var pastOrderList:ArrayList<Data>?=ArrayList()

    fun setData(inProgresParams:ArrayList<Data>?,pastOrderParams:ArrayList<Data>?) {
        inProgressList=inProgresParams
        pastOrderList=pastOrderParams
    }
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
                val bundle=Bundle()
                bundle.putParcelableArrayList("data",inProgressList)
                fragment.arguments=bundle
                return fragment
            }
            1 -> {
                fragment = PastorderFragment()
                val bundle=Bundle()
                bundle.putParcelableArrayList("data",pastOrderList)
                fragment.arguments=bundle
                return fragment
            }
            else -> {
                fragment = InprogresFragment()
                val bundle=Bundle()
                bundle.putParcelableArrayList("data",inProgressList)
                fragment.arguments=bundle
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

}