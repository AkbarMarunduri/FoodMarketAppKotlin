package com.akbarprojec.foodmarket_kotlin.ui.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.home.Data
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.item_home_horizontal.view.*
import kotlinx.android.synthetic.main.item_home_horizontal.view.ivPoster
import kotlinx.android.synthetic.main.item_home_horizontal.view.tvTitle

class HomeAdapter(
    private val listData: List<Data>,
    private val itemAdapterCallBack: ItemAdapterCallBack
) : RecyclerView.Adapter<HomeAdapter.OrdeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.OrdeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_horizontal, parent, false)
        return OrdeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.OrdeViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class OrdeViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {

        fun bind(data: Data, itemAdapterCallBack: ItemAdapterCallBack) {

            itemView.apply {
                tvTitle.text = data.name
                rbFood.rating = data.rate?.toFloat()!!

                Glide.with(context)
                    .load(data.picturePath)
                    .into(ivPoster)

                itemView.setOnClickListener {
                    itemAdapterCallBack.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallBack { fun onClick(v: View, data: Data) }
}