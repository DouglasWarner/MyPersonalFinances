package com.douglas.mypersonalfinances.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.douglas.mypersonalfinances.data.model.itemAdapter.MonthTransactionAdapterItem
import com.douglas.mypersonalfinances.databinding.ItemMonthOverviewBinding
import com.douglas.mypersonalfinances.ui.holders.MonthOverviewHolder

class MonthOverviewAdapter(
    val context: Context,
    val data: ArrayList<MonthTransactionAdapterItem> = ArrayList()
) :
    RecyclerView.Adapter<MonthOverviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthOverviewHolder {
        return MonthOverviewHolder(
            ItemMonthOverviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MonthOverviewHolder, position: Int) {
        holder.bindData(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun reloadData(monthOverview: ArrayList<MonthTransactionAdapterItem>) {
        this.data.clear()
        this.data.addAll(monthOverview)
        notifyDataSetChanged()
    }
}