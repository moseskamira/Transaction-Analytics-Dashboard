package com.pay.payanalysis.model

import com.google.gson.annotations.SerializedName


data class Transactions (

  @SerializedName("date"        ) var date        : String? = null,
  @SerializedName("description" ) var description : String? = null,
  @SerializedName("amount"      ) var amount      : Int?    = null

)