package com.pay.payanalysis.retrofit

import com.pay.payanalysis.constants.Constants
import com.pay.payanalysis.model.Transactions
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    companion object {
        var apiService: APIService? = null
        fun getAPIServiceInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }

    @GET(Apis.txn)
    suspend fun getTransactions(): List<Transactions>
}