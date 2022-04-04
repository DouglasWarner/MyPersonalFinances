package com.douglas.mypersonalfinances.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.douglas.mypersonalfinances.data.model.TypeTransaction
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.databinding.ItemDayTransactionBinding
import com.douglas.mypersonalfinances.utils.CommonUtils
import java.util.*

class DayTransactionHolder(private val binding: ItemDayTransactionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: MyTransaction, onItemClickListener: (MyTransaction) -> Unit) {
        when (data.type) {
            TypeTransaction.Expenses -> {
                binding.tvTransactionDate.text =
                    data.dateTime.toString(CommonUtils.DATEFORMAT, Locale.getDefault())
                binding.tvAmount.text = data.amount.toString()
                binding.tvDescription.text = data.description
            }
            TypeTransaction.Incomes -> {
                binding.tvTransactionDate.text =
                    data.dateTime.toString(CommonUtils.DATEFORMAT, Locale.getDefault())
                binding.tvAmount.text = data.amount.toString()
                binding.tvDescription.text = data.description
            }
        }

        binding.root.setOnClickListener { onItemClickListener(data) }
    }
}