package com.douglas.mypersonalfinances.ui.dayoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.douglas.mypersonalfinances.data.MpfDatabase
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.databinding.FragmentDayTransactionsBinding
import com.douglas.mypersonalfinances.ui.adapters.DayTransactionAdapter
import com.douglas.mypersonalfinances.utils.CommonUtils.Companion.DATEFORMAT
import com.google.android.material.tabs.TabLayout
import java.util.*

class DayTransactionsFragment : Fragment() {

    private val dayTransactionsViewModel: DayOverviewViewModel by viewModels()

    private lateinit var binding: FragmentDayTransactionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        FragmentDayTransactionsBinding.inflate(layoutInflater).apply {
            binding = this
            setHasOptionsMenu(true)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initListeners()
        initObservers()
    }

    private fun initRecyclerView() {
        binding.rvTransactions.apply {
            adapter = DayTransactionAdapter(context, ArrayList()) { transaction ->
                onItemClick(transaction)
            }
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }

    private fun initListeners() {
        binding.tblTransactions.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                manageTabSelected(tab?.position)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                manageTabSelected(tab?.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })

        binding.tblTransactions.getTabAt(0)?.select()
    }

    private fun initObservers() {
        dayTransactionsViewModel.displayDateResult.observe(viewLifecycleOwner) { displayDate ->
            binding.tvDate.text = displayDate.toString(DATEFORMAT, Locale.getDefault())
        }

//        dayTransactionsViewModel.listDataResult.observe(viewLifecycleOwner) { dayResult ->
//            fillTabSelectedAdapterData(dayResult)
//        }
    }

    private fun onItemClick(myTransaction: MyTransaction) {
//        findNavController().navigate(R.id.manageMyTransaction, Bundle().apply {
//            putSerializable(MyTransaction.TAG, myTransaction)
//        })
    }

    private fun manageTabSelected(tabPosition: Int?) {
        when (tabPosition ?: 0) {
            0 -> {
                dayTransactionsViewModel.loadExpenses()
            }
            1 -> {
                dayTransactionsViewModel.loadIncomes()
            }
        }
    }

    private fun fillTabSelectedAdapterData(dayResult: DayTransactionResult) {
        when (binding.tblTransactions.selectedTabPosition) {
            0 -> {
                dayResult.listExpense?.let { expenses ->
                    (binding.rvTransactions.adapter as DayTransactionAdapter)
                        .reloadData(expenses)
                }
            }
            1 -> {
                dayResult.listIncome?.let { incomes ->
                    (binding.rvTransactions.adapter as DayTransactionAdapter)
                        .reloadData(incomes)
                }
            }
        }
    }
}