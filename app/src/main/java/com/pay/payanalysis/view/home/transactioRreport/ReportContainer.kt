package com.pay.payanalysis.view.home.transactioRreport

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Transactions
import com.pay.payanalysis.repository.TransactionRepository
import com.pay.payanalysis.view.home.analytics.StatementSummary
import com.pay.payanalysis.view.reUsable.CustomDivider
import com.pay.payanalysis.viewModel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ReportContainer() {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(top = 40.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxHeight()
    ) {
        val fullTransactionList: MutableList<Transactions> = ArrayList()
        val selectedList = mutableListOf<Transactions>()
        val transactionViewModel = TransactionViewModel(
            TransactionRepository()
        )
        var isExpanded by remember {
            mutableStateOf(false)
        }
        var isGroupExpanded by remember {
            mutableStateOf(false)
        }
        var filterGroup by remember {
            mutableStateOf("")
        }
        var filterType by remember {
            mutableStateOf("")
        }
        val filterGroupList = mutableListOf("DATE", "TYPE", "AMOUNT", "CATEGORY")
        val myStatement = transactionViewModel.getStatement(LocalContext.current);
        fullTransactionList.addAll(myStatement.customer!!.account!!.transactions)
        for (txn in fullTransactionList) {
            if (filterGroup == "CATEGORY") {
                if (txn.category!!.contains(filterType)) {
                    selectedList.add(txn)
                }

            } else if (filterGroup == "AMOUNT") {
                if (txn.amount.toString() == filterType) {
                    selectedList.add(txn)
                }

            } else if (filterGroup == "TYPE") {
                if (txn.type!!.contains(filterType)) {
                    selectedList.add(txn)
                }

            } else {
                selectedList.clear()
                if (txn.date!!.contains(filterType)) {
                    selectedList.add(txn)
                }

            }

        }
        Row {
            Text(
                text = "FILTER GROUP:  ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                )
            )
            ExposedDropdownMenuBox(
                expanded = isGroupExpanded,
                onExpandedChange = { newValue ->
                    isGroupExpanded = newValue
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = filterGroup,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "Select Filter Group")
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isGroupExpanded,
                    onDismissRequest = {
                        isGroupExpanded = false
                    },
                    modifier = Modifier
                        .background(color = colorResource(id = R.color.white))
                        .clip(shape = RectangleShape)

                ) {
                    filterGroupList.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(text = option)
                            },
                            onClick = {
                                filterGroup = option
                                isGroupExpanded = false
                            }
                        )
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SUB FILTER:  ",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                )
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue ->
                    isExpanded = newValue
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = filterType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "Select Sub Filter")
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
                        if (filterGroup == "CATEGORY") {
                            dates.add(it.category!!)
                        } else if (filterGroup == "AMOUNT") {
                            dates.add(it.amount.toString())
                        } else if (filterGroup == "TYPE") {
                            dates.add(it.type!!)
                        } else {
                            dates.add(it.date!!)
                        }
                    }
                    val uniqueDates = dates.toSet().toList()
                    uniqueDates.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(text = option)
                            },
                            onClick = {
                                filterType = option
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
        Text(
            text = "TRANSACTION REPORT AS TO SELECTED FILTER",
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp),
            color = colorResource(id = R.color.blue_200),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        CustomDivider()
        StatementReport(transList = selectedList)
        Spacer(modifier = Modifier.height(80.dp))
    }
}





