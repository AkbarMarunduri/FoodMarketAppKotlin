package com.akbarprojec.foodmarket_kotlin.ui.order.pastorder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.Data
import kotlinx.android.synthetic.main.fragment_profile_account.*

class PastorderFragment : Fragment(), PastorderAdapter.ItemAdapterCallBack {

    private var postOrderList: ArrayList<Data>? = ArrayList()
    private var adaptor: PastorderAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inprogress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        postOrderList = arguments?.getParcelableArrayList("data")
        adaptor = PastorderAdapter(postOrderList!!, this)
        var layoutManeger = LinearLayoutManager(activity)
        rvList.layoutManager = layoutManeger
        rvList.adapter = adaptor

    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity, "Clicked data no ${data.id}", Toast.LENGTH_SHORT).show()
    }
}