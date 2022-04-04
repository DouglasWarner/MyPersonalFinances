package com.douglas.mypersonalfinances.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.douglas.mypersonalfinances.data.model.itemAdapter.MonthPlannedAdapterItem
import com.douglas.mypersonalfinances.data.model.itemAdapter.MonthTransactionAdapterItem
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.databinding.ItemMonthOverviewBinding

class MonthOverviewHolder(private val binding: ItemMonthOverviewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: MonthTransactionAdapterItem) {
        bindMonth(data.month)
        bindExpense(data.expensesList)
        bindIncome(data.incomesList)
        bindPlanned(data.plannedData)
    }

    fun bindMonth(month: String) {
        binding.tvMonthHeader.text = month
    }

    fun bindIncome(incomes: List<MyTransaction>) {
        incomes.forEach {
            binding.donutIncome.addAmount(
                sectionName = it.category,
                amount = it.amount
            )
        }
    }

    fun bindExpense(expenses: List<MyTransaction>) {
        expenses.forEach {
            binding.donutExpenses.addAmount(
                sectionName = it.category,
                amount = it.amount
            )

        }
    }

    fun bindPlanned(planned: MonthPlannedAdapterItem?) {
        planned?.let { data ->
            binding.tvMonthHeader.text = data.month
            binding.donutExpenses.cap = data.totalPlannedExpense
            binding.donutIncome.cap = data.totalPlannedIncome
        }
    }
}