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
import androidx.compose.material3.LocalTextStyle
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
import com.pay.payanalysis.view.reUsable.CustomDivider
import com.pay.payanalysis.viewModel.TransactionViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TextualReportTabContainer() {
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
        val filterGroupList = mutableListOf("TYPE", "AMOUNT", "CATEGORY")
        val myStatement = transactionViewModel.getStatement(LocalContext.current)
        fullTransactionList.addAll(myStatement.customer!!.account!!.transactions)
        for (txn in fullTransactionList) {
            when (filterGroup) {
                "CATEGORY" -> {
                    if (txn.category!!.contains(filterType)) {
                        selectedList.add(txn)
                    }
                }

                "AMOUNT" -> {
                    if (txn.amount.toString() == filterType) {
                        selectedList.add(txn)
                    }
                }

                "TYPE" -> {
                    if (txn.type!!.contains(filterType)) {
                        selectedList.add(txn)
                    }
                }

                else -> {
                    selectedList.clear()
                    if (txn.date!!.contains(filterType)) {
                        selectedList.add(txn)
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
                text = "FILTER:",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
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
                    value = filterGroup,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "Select Filter Group", textAlign = TextAlign.Start)
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
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start)
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),

            ) {
            Text(
                text = "SUB FILTER:",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp
                ),
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
                    value = filterType.uppercase(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "Select Sub Filter", textAlign = TextAlign.End)
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
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start)
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
                        when (filterGroup) {
                            "CATEGORY" -> {
                                dropDownList.add(it.category!!)
                            }

                            "AMOUNT" -> {
                                dropDownList.add(it.amount.toString())
                            }

                            "TYPE" -> {
                                dropDownList.add(it.type!!)
                            }
                        }
                    }
                    val uniqueDates = dropDownList.toSet().toList()
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
        Text(
            text = "TRANSACTION REPORT AS TO SELECTED FILTER",
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp),
            color = colorResource(id = R.color.blue_200),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
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
                fontSize = 18.sp,
                color = colorResource(id = R.color.blue_200),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Column {
                if (depositTotalAmount != 0.0)
                    Text(
                        text = "Deposit: UGX $depositTotalAmount",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.blue_200),
                        fontWeight = FontWeight.Bold
                    )
                if (withdrawalTotal != 0.0)
                    Text(
                        text = "Withdraw: UGX $withdrawalTotal",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.blue_200),
                        fontWeight = FontWeight.Bold
                    )
            }
        }
        CustomDivider()
        StatementReport(transList = selectedList)
        Spacer(modifier = Modifier.height(80.dp))

    }
}





