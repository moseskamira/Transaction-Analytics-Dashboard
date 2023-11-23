package com.pay.payanalysis.model

import com.google.gson.annotations.SerializedName


data class FinancialStatement(
    @SerializedName("customer") var customer: Customer? = Customer()

)