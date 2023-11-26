package com.pay.payanalysis.model

import com.google.gson.annotations.SerializedName


data class Transactions(
    @SerializedName("date") var date: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("amount") var amount: Int? = null,
    @SerializedName("service") var service: String? = null,
    @SerializedName("category") var category: String? = null,

    )