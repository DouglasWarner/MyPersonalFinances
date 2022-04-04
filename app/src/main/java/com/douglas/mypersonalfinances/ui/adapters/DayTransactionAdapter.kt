package com.douglas.mypersonalfinances.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas.mypersonalfinances.data.model.transaction.MyTransaction
import com.douglas.mypersonalfinances.databinding.ItemDayTransactionBinding
import com.douglas.mypersonalfinances.ui.holders.DayTransactionHolder

class DayTransactionAdapter(
    val context: Context,
    private val data: ArrayList<MyTransaction> = ArrayList(),
    private val onItemClickListener: (MyTransaction) -> Unit
) :
    RecyclerView.Adapter<DayTransactionHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayTransactionHolder {
        return DayTransactionHolder(
            ItemDayTransactionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DayTransactionHolder, position: Int) {
        if (!data.isNullOrEmpty()) {
            holder.bind(data[position], onItemClickListener)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun reloadData(it: List<MyTransaction>) {
        this.data.clear()
        this.data.addAll(it)
        notifyDataSetChanged()
    }
}