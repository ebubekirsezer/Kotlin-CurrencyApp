package com.ess.currencyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ess.currencyapp.R
import com.ess.currencyapp.adapter.CurrencyRecyclerViewAdapter
import com.ess.currencyapp.model.Currency
import com.ess.currencyapp.model.Rates
import com.ess.currencyapp.service.CurrencyAPI
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    val BASE_URL = "https://api.exchangeratesapi.io/"

    val currencyNames : ArrayList<String> = arrayListOf("AUD","BGN","BRL","CAD","CHF","CNY","CZK","DKK","GBP","HKD","HRK","HUF","IDR",
    "ILS","INR","ISK","JPY","KRW","MXN","MYR","NOK","NZD","PHP","PLN","RON","RUB","SEK","SGD","THB","TRY","USD","ZAR")
    val currencyValues: ArrayList<Double> = arrayListOf()
    var currencyRecyclerViewAdapter: CurrencyRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyList.layoutManager = LinearLayoutManager(context)
        getCurrencyRates()
    }

    fun getCurrencyRates(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyAPI::class.java)
        val call = service.getCurrency()

        call.enqueue(object : Callback<Currency>{
            override fun onFailure(call: Call<Currency>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Currency>, response: Response<Currency>) {
                if(response.isSuccessful){
                    response.body()?.let {
                        addAllCurrencyValues(it.rates)
                        currencyRecyclerViewAdapter = CurrencyRecyclerViewAdapter(currencyNames,currencyValues)
                        currencyList.adapter = currencyRecyclerViewAdapter
                    }
                }
            }

        })
    }

    fun addAllCurrencyValues(rateModel: Rates){
            currencyValues.add(rateModel.AUD)
            currencyValues.add(rateModel.BGN)
            currencyValues.add(rateModel.BRL)
            currencyValues.add(rateModel.CAD)
            currencyValues.add(rateModel.CHF)
            currencyValues.add(rateModel.CNY)
            currencyValues.add(rateModel.CZK)
            currencyValues.add(rateModel.DKK)
            currencyValues.add(rateModel.GBP)
            currencyValues.add(rateModel.HKD)
            currencyValues.add(rateModel.HRK)
            currencyValues.add(rateModel.HUF)
            currencyValues.add(rateModel.IDR)
            currencyValues.add(rateModel.ILS)
            currencyValues.add(rateModel.INR)
            currencyValues.add(rateModel.ISK)
            currencyValues.add(rateModel.JPY)
            currencyValues.add(rateModel.KRW)
            currencyValues.add(rateModel.MXN)
            currencyValues.add(rateModel.MYR)
            currencyValues.add(rateModel.NOK)
            currencyValues.add(rateModel.NZD)
            currencyValues.add(rateModel.PHP)
            currencyValues.add(rateModel.PLN)
            currencyValues.add(rateModel.RON)
            currencyValues.add(rateModel.RUB)
            currencyValues.add(rateModel.SEK)
            currencyValues.add(rateModel.SGD)
            currencyValues.add(rateModel.THB)
            currencyValues.add(rateModel.TRY)
            currencyValues.add(rateModel.USD)
            currencyValues.add(rateModel.ZAR)
    }
}