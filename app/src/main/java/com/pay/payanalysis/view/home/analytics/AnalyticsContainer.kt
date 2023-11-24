package com.pay.payanalysis.view.home.analytics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Transactions
import com.pay.payanalysis.repository.TransactionRepository
import com.pay.payanalysis.viewModel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AnalyticsContainer() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(top = 40.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        val fullTransactionList: MutableList<Transactions> = ArrayList()
        val selectedList = mutableListOf<Transactions>()
        val entries: MutableList<PieChartEntry> = mutableListOf()
        val transactionViewModel = TransactionViewModel(
            TransactionRepository()
        )
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var selectedYear by remember {
            mutableStateOf("")
        }
        val myStatement = transactionViewModel.getStatement(LocalContext.current);
        fullTransactionList.addAll(myStatement.customer!!.account!!.transactions)
        for (txn in fullTransactionList) {
            if (txn.date!!.contains(selectedYear)) {
                selectedList.add(txn)
            }
        }
        var totalTransAmount = 0.0
        for (trans in selectedList) {
            totalTransAmount += (trans.amount!!).toDouble()
        }
        val sortedList = selectedList.sortedBy { it.type }
        for (tran in sortedList) {
            if (tran.type == "Deposit") {
                val bgColor: Color = colorResource(id = R.color.blue_200)
                entries.add(
                    PieChartEntry(
                        bgColor,
                        ((tran.amount!! / totalTransAmount)).toFloat()
                    )
                )
            } else if (tran.type == "Withdraw") {
                val bgColor: Color = MaterialTheme.colors.primaryVariant
                entries.add(
                    PieChartEntry(
                        bgColor,
                        ((tran.amount!! / totalTransAmount)).toFloat()
                    )
                )

            } else {
                val bgColor: Color = colorResource(id = R.color.teal_200)
                entries.add(
                    PieChartEntry(
                        bgColor,
                        ((tran.amount!! / totalTransAmount)).toFloat()
                    )
                )
            }

        }

        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()) {
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue ->
                    isExpanded = newValue
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = selectedYear,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "Select Year")
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                    },
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.white))
                        .clip(shape = RectangleShape)

                ) {
                    val dates = mutableListOf<String>()
                    fullTransactionList.forEach {
                        dates.add(it.date!!.substring(0, 4))
                    }
                    val uniqueDates = dates.toSet().toList()
                    uniqueDates.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(text = option)
                            },
                            onClick = {
                                selectedYear = option
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        StatementSummary(customer = myStatement.customer!!)
        Spacer(modifier = Modifier.height(20.dp))
        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()) {
            DataPieChart(entries)
        }
        Spacer(modifier = Modifier.height(80.dp))
    }

}

