package com.akbarprojec.foodmarket_kotlin.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.model.response.transaction.TransactionResponse
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment(),OrderContract.View {
    lateinit var presenter: OrderPresenter
    var progresdialog:Dialog?=null

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
        presenter=OrderPresenter(this)
        presenter.getTransaction()

        val orderSelecionadapter=OrderSelectionPagerAdapter(childFragmentManager)
        viewPager.adapter=orderSelecionadapter
        tabLayout.setupWithViewPager(viewPager)
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
//        if (transactionResponse.data) {
//        }
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