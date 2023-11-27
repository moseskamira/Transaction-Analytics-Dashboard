package com.pay.payanalysis.view.home.reportTab

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Transactions
import com.pay.payanalysis.repository.TransactionRepository
import com.pay.payanalysis.ui.theme.Typography
import com.pay.payanalysis.view.reUsable.CustomDivider
import com.pay.payanalysis.viewModel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TextualReportTabContainer() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
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
        var filter by remember {
            mutableStateOf("")
        }
        var subFilter by remember {
            mutableStateOf("")
        }
        val filterGroupList = mutableListOf("Type", "Amount", "Category")
        val myStatement = transactionViewModel.getStatement(LocalContext.current)
        fullTransactionList.addAll(myStatement.customer!!.account!!.transactions)
        for (txn in fullTransactionList) {
            when (filter) {
                "Category" -> {
                    if (txn.category!!.contains(subFilter)) {
                        selectedList.add(txn)
                    }
                }

                "Amount" -> {
                    if (txn.amount.toString() == subFilter) {
                        selectedList.add(txn)
                    }
                }

                "Type" -> {
                    if (txn.type!!.contains(subFilter)) {
                        selectedList.add(txn)
                    }
                }

                else -> {
                    selectedList.clear()
                    if (txn.date!!.contains(subFilter)) {
                        selectedList.add(txn)
                    }
                }
            }
        }
        Text(
            text = "TEXTUAL TRANSACTION REPORT",
            style = Typography.titleMedium,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.blue_200),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),

            ) {
            Text(
                text = "FILTER:",
                style = Typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            ExposedDropdownMenuBox(
                expanded = isGroupExpanded,
                onExpandedChange = { newValue ->
                    isGroupExpanded = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                TextField(
                    value = filter.uppercase(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(
                            text = "SELECT FILTER",
                            textAlign = TextAlign.Start,
                            style = Typography.bodyLarge,
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        textColor = colorResource(id = R.color.black),
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.White,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier.menuAnchor(),
                    textStyle = Typography.bodyLarge
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
                                Text(text = option.uppercase(), style = Typography.bodyLarge)
                            },
                            onClick = {
                                filter = option
                                isGroupExpanded = false
                            }
                        )
                    }
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),

            ) {
            Text(
                text = "SUB FILTER:",
                style = Typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            ExposedDropdownMenuBox(
                expanded = isExpanded,
                onExpandedChange = { newValue ->
                    isExpanded = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            ) {
                TextField(
                    value = subFilter.uppercase(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(
                            text = "SELECT SUB FILTER",
                            textAlign = TextAlign.Start,
                            style = Typography.bodyLarge,
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        textColor = colorResource(id = R.color.black),
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.White,
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier.menuAnchor(),
                    textStyle = Typography.bodyLarge
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
                    val dropDownList = mutableListOf<String>()
                    fullTransactionList.forEach {
                        when (filter) {
                            "Category" -> {
                                dropDownList.add(it.category!!)
                            }

                            "Amount" -> {
                                dropDownList.add(it.amount.toString())
                            }

                            "Type" -> {
                                dropDownList.add(it.type!!)
                            }
                        }
                    }
                    val uniqueDates = dropDownList.toSet().toList()
                    uniqueDates.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(text = option.uppercase(), style = Typography.bodyLarge)
                            },
                            onClick = {
                                subFilter = option
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
        CustomDivider()
        Row(verticalAlignment = Alignment.CenterVertically) {
            var depositTotalAmount = 0.0
            var withdrawalTotal = 0.0
            selectedList.forEach {
                if (it.type == "Deposit") {
                    depositTotalAmount += it.amount!!
                } else {
                    withdrawalTotal += it.amount!!
                }

            }
            Text(
                text = "Count: (${selectedList.size})",
                style = Typography.bodyLarge,
                color = colorResource(id = R.color.black),

                )
            Spacer(modifier = Modifier.weight(1f))
            Column {
                if (depositTotalAmount != 0.0)
                    Text(
                        text = "Deposit: UGX $depositTotalAmount",
                        style = Typography.bodyLarge,
                        color = colorResource(id = R.color.black),

                        )
                if (withdrawalTotal != 0.0)
                    Text(
                        text = "Withdraw: UGX $withdrawalTotal",
                        style = Typography.bodyLarge,
                        color = colorResource(id = R.color.black),
                    )
            }
        }
        CustomDivider()
        StatementReport(transList = selectedList)
        Spacer(modifier = Modifier.height(80.dp))

    }
}





