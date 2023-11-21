package com.pay.payanalysis.repository

import android.content.Context
import com.google.gson.Gson
import com.pay.payanalysis.model.FinancialStatement
import com.pay.payanalysis.retrofit.APIService


class TransactionRepository {
    fun getStatement(context: Context): FinancialStatement {
        val jsonString = context.assets.open("statement.json")
            .bufferedReader()
            .use { it.readText() }
        return Gson().fromJson(jsonString, FinancialStatement::class.java)
    }


}
