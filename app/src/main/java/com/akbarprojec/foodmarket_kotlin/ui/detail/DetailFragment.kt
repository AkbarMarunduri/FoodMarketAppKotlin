package com.akbarprojec.foodmarket_kotlin.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.home.Data
import com.akbarprojec.foodmarket_kotlin.utils.Helpers.formatPrice
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.ivPoster
import kotlinx.android.synthetic.main.fragment_detail.tvTitle



class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        arguments?.let {
            DetailFragmentArgs.fromBundle(it).data.let {
                initView(it)
            }
        }
        //initView(arguments?.getParcelable("data"))

        buttonOrderNow.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_payment)
        }
    }

    private fun initView(data: Data?) {
        Glide.with(requireContext())
            .load(data?.picturePath)
            .into(ivPoster)

        tvTitle.text=data?.name
        tvDesc.text=data?.description
        tvInggredints.text=data?.ingredients
        if (data != null) {
            tvHarga.formatPrice(data.price.toString())
        }
    }


}