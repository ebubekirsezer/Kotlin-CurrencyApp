package com.ess.currencyapp.service

import com.ess.currencyapp.model.Currency
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyAPI {

    @GET("/latest")
    fun getCurrency() : Call<Currency>
}