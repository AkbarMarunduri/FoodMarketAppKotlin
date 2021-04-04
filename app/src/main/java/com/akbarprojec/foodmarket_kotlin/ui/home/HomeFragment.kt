
package com.akbarprojec.foodmarket_kotlin.ui.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.FoodMarket
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.home.Data
import com.akbarprojec.foodmarket_kotlin.model.response.home.HomeResponse
import com.akbarprojec.foodmarket_kotlin.model.response.login.User
import com.akbarprojec.foodmarket_kotlin.ui.detail.DetailActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_singup.*
import kotlinx.android.synthetic.main.fragment_singup.ivProfile as ivProfile1

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallBack, HomeContract.View {
    private var newStateList: ArrayList<Data> = ArrayList()
    private var popularLList: ArrayList<Data> = ArrayList()
    private var recomendeList: ArrayList<Data> = ArrayList()

    private lateinit var presenter: HomePresenter
    var progresDiaog: Dialog? = null

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
        presenter = HomePresenter(this)
        presenter.getHome()

        initView();
    }


    private fun initView() {
        //mengatur loading style
        progresDiaog = Dialog(requireContext())
        val dialogLLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progresDiaog?.let {
            it.setContentView(dialogLLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        //load photo profile user dari sahare preference
        var user=FoodMarket.getApp().getUser()
        var userResponse=Gson().fromJson(user,User::class.java)
        if (!userResponse.profile_photo_url.isNullOrEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profile_photo_url)
                .into(ivProfile)
        }
    }


    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data",data)
        startActivity(detail)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        for (a in homeResponse.data.indices) {
            var items: List<String> = homeResponse.data[a].types?.split(",") ?: ArrayList()
            //exp hasil items=arrayOfString("new_state","recomended")
            for (x in items.indices) {
                if (items[x].equals("new_food", true)) {
                    newStateList?.add(homeResponse.data[a])
                } else if (items[x].equals("recommended")) {
                    recomendeList?.add(homeResponse.data[a])
                } else if (items[x].equals("popular")) {
                    popularLList?.add(homeResponse.data[a])
                }
            }
        }

        var adapter = HomeAdapter(homeResponse.data, this)
        val layoutManeger: RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rvList.layoutManager = layoutManeger
        rvList.adapter = adapter

        //setting viewPagger
        val selectionPagerAdapter = SelectionPagerAdapter(childFragmentManager)
        selectionPagerAdapter.setData(newStateList, popularLList, recomendeList)
        viewPager.adapter = selectionPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onHomeFailed(massage: String) {
        Toast.makeText(activity, massage, Toast.LENGTH_SHORT).show()
    }

    override fun showLLoading() {
        progresDiaog?.show()
    }

    override fun dismisLoading() {
        progresDiaog?.dismiss()
    }
}