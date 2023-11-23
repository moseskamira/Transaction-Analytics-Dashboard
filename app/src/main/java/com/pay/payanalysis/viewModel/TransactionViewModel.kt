package com.pay.payanalysis.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pay.payanalysis.repository.TransactionRepository
import com.pay.payanalysis.model.FinancialStatement
import com.pay.payanalysis.retrofit.APIService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {
    fun getStatement(context: Context): FinancialStatement {
        val apiService = APIService.getAPIServiceInstance()
        val myStatement = mutableStateOf(FinancialStatement())
        viewModelScope.launch {
            try {
                // Because we are using Local Data file Json, we do not call the api hence commented line below
                //apiService.getTransactions()

                val statement = repository.getStatement(context = context)
                myStatement.value = statement
            } catch (e: IOException) {
                print(e.message)

            }
        }
        return myStatement.value
    }
}