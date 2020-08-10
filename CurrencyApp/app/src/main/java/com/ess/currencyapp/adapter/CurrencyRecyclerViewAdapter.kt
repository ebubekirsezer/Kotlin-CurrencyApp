package com.ess.currencyapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ess.currencyapp.R
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrencyRecyclerViewAdapter(private val currencyNameList: ArrayList<String>, private val currencyValueList: ArrayList<Double>)
    : RecyclerView.Adapter<CurrencyRecyclerViewAdapter.RowHolder>() {

    class RowHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(currencyName: String,currencyValue: Double){
            itemView.currency_name.text = currencyName
            itemView.currency_value.text = currencyValue.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_currency,parent,false)

        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return currencyNameList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(currencyNameList[position],currencyValueList[position])
    }
}