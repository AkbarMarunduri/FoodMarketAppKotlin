package com.akbarprojec.foodmarket_kotlin.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.Data
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.TransactionResponse
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment(), OrderContract.View {
    lateinit var presenter: OrderPresenter
    var progresdialog: Dialog? = null
    var inProgresList: ArrayList<Data>? = ArrayList()
    var inPostOrderList: ArrayList<Data>? = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    fun initView() {
        progresdialog = Dialog(requireContext())
        val dialogLLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progresdialog?.let {
            it.setContentView(dialogLLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        if (transactionResponse.data.isNullOrEmpty()) {
            ll_empty.visibility = View.VISIBLE
            tabLayout.visibility = View.GONE
            include_toolbar.visibility = View.GONE
        } else {
            for (a in transactionResponse.data.indices) {
                if (transactionResponse.data[a].status.equals("ON_DELIVERY", true)
                    || transactionResponse.data[a].status.equals("PENDING", true)
                ) {
                    inProgresList?.add(transactionResponse.data[a])
                } else if (transactionResponse.data[a].status.equals("DELIVERED", true)
                    || transactionResponse.data[a].status.equals("CANCELLED", true)
                    || transactionResponse.data[a].status.equals("SUCCESS", true)
                ) {
                    inPostOrderList?.add(transactionResponse.data[a])
                }

                val selectionPagerAdapator=OrderSelectionPagerAdapter(childFragmentManager)
                selectionPagerAdapator.setData(inProgresList, inPostOrderList)
                viewPager.adapter=selectionPagerAdapator
                tabLayout.setupWithViewPager(viewPager)
            }
        }


    }

    override fun onTransactionFailed(massage: String) {
        Toast.makeText(activity, massage, Toast.LENGTH_LONG).show()
    }

    override fun showLLoading() {
        progresdialog?.show()
    }

    override fun dismisLoading() {
        progresdialog?.dismiss()
    }
}