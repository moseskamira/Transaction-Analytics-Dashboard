package com.pay.payanalysis.model

import com.google.gson.annotations.SerializedName


data class Customer(

    @SerializedName("name") var name: String? = null,
    @SerializedName("age") var age: Int? = null,
    @SerializedName("gender") var gender: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("account") var account: Account? = Account()

)