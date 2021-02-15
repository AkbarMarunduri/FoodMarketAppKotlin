package com.akbarprojec.foodmarket_kotlin.ui.home.newtaste


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.dummy.HomeVerticalModel
import com.akbarprojec.foodmarket_kotlin.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_home_horizontal.view.rbFood
import kotlinx.android.synthetic.main.item_home_horizontal.view.tvTitle
import kotlinx.android.synthetic.main.item_home_vertical.view.*


class HomeNewTasteAdapter(
    private val listData: List<HomeVerticalModel>,
    private val itemAdapterCallBack: ItemAdapterCallBack
) : RecyclerView.Adapter<HomeNewTasteAdapter.OrdeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeNewTasteAdapter.OrdeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return OrdeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeNewTasteAdapter.OrdeViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallBack)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class OrdeViewHolder(Itemview: View) : RecyclerView.ViewHolder(Itemview) {

        fun bind(data: HomeVerticalModel, itemAdapterCallBack: ItemAdapterCallBack) {
            itemView.apply {
                tvTitle.text = data.title
                rbFood.rating = data.rating
                tvPrice.formatPrice(data.price)

//                Glide.with(context)
//                    .load(data.src)
//                    .into(ivPoster)

                itemView.setOnClickListener {
                    itemAdapterCallBack.onClick(it, data)
                }
            }
        }
    }

    interface ItemAdapterCallBack {
        fun onClick(v: View, data: HomeVerticalModel)
    }
}