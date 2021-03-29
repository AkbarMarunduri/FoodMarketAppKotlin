package com.akbarprojec.foodmarket_kotlin.ui.order.inprogress

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.Data
import kotlinx.android.synthetic.main.fragment_profile_account.rvList

class InprogresFragment : Fragment(), InprogressAdapter.ItemAdapterCallBack {

    private var adapter: InprogressAdapter? = null
    private var inProgresList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inprogress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inProgresList = arguments?.getParcelableArrayList("data")

        if (!inProgresList.isNullOrEmpty()) {
            adapter = InprogressAdapter(inProgresList!!, this)
            val layoutmaneger = LinearLayoutManager(activity)
            rvList.layoutManager = layoutmaneger
            rvList.adapter = adapter
        }
    }

    override fun onClick(v: View, data: Data) {
        Toast.makeText(activity, "Clicked pada data ${data.id}", Toast.LENGTH_SHORT).show()
    }


}