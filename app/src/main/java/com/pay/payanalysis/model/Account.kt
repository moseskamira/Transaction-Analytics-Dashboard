package com.pay.payanalysis.model

import com.google.gson.annotations.SerializedName


data class Account(

    @SerializedName("account_number") var accountNumber: String? = null,
    @SerializedName("balance") var balance: Int? = null,
    @SerializedName("transactions") var transactions: ArrayList<Transactions> = arrayListOf()

)