package com.akbarprojec.foodmarket_kotlin.ui.profile


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.dummy.HomeModel
import com.akbarprojec.foodmarket_kotlin.model.dummy.ProfileMenuModel
import kotlinx.android.synthetic.main.item_home_horizontal.view.*
import kotlinx.android.synthetic.main.item_home_horizontal.view.tvTitle
import kotlinx.android.synthetic.main.item_menu_profile.view.*

class ProfileMenuAdapter(
    private val listData: List<ProfileMenuModel>,
    private val itemAdapterCallBack: ItemAdapterCallBack
) : RecyclerView.Adapter<ProfileMenuAdapter.OrdeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuAdapter.OrdeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_menu_profile, parent, false)
        return OrdeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileMenuAdapter.OrdeViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class OrdeViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {

        fun bind(data: ProfileMenuModel, itemAdapterCallBack: ItemAdapterCallBack) {
            itemView.apply {
                tvTitle.text = data.title


//                Glide.with(context)
//                    .load(data.src)
//                    .into(ivPoster)

                itemView.setOnClickListener {
                    itemAdapterCallBack.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallBack { fun onClick(v: View, data: ProfileMenuModel) }
}