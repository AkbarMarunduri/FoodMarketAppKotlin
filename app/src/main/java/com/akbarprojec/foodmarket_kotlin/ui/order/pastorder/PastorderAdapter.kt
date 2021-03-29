package com.akbarprojec.foodmarket_kotlin.ui.order.pastorder


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.Data
import com.akbarprojec.foodmarket_kotlin.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pastorder.view.*


class PastorderAdapter(
    private val listData: List<Data>,
    private val itemAdapterCallBack: ItemAdapterCallBack
) : RecyclerView.Adapter<PastorderAdapter.OrdeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PastorderAdapter.OrdeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_pastorder, parent, false)
        return OrdeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PastorderAdapter.OrdeViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class OrdeViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {

        fun bind(data: Data, itemAdapterCallBack: ItemAdapterCallBack) {

            itemView.apply {
                tvTitle.text = data.food.name
                tvPrice.formatPrice(data.food.price.toString())
                Glide.with(context)
                    .load(data.food.picturePath)
                    .into(ivPoster)

                itemView.setOnClickListener {
                    itemAdapterCallBack.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallBack {
        fun onClick(v: View, data: Data)
    }
}