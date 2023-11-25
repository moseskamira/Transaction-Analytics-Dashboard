package com.pay.payanalysis.view.home.graphTab

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.LocalTextStyle
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Transactions
import com.pay.payanalysis.repository.TransactionRepository
import com.pay.payanalysis.view.reUsable.CustomDivider
import com.pay.payanalysis.viewModel.TransactionViewModel

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun GraphTabContainer() {
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
        var selectedCategory by remember {
            mutableStateOf("")
        }
        var showChart by remember {
            mutableStateOf(true)
        }
        val myStatement = transactionViewModel.getStatement(LocalContext.current)
        fullTransactionList.addAll(myStatement.customer!!.account!!.transactions)
        for (txn in fullTransactionList) {
            if (txn.type!!.contains(selectedCategory)) {
                selectedList.add(txn)
            }
        }
        var totalTransAmount = 0.0
        for (trans in selectedList) {
            totalTransAmount += (trans.amount!!).toDouble()
        }
        val sortedList = selectedList.sortedBy { it.type }
        for (tran in sortedList) {
            when (tran.type) {
                "Deposit" -> {
                    val bgColor: Color = colorResource(id = R.color.blue_200)
                    entries.add(
                        PieChartEntry(
                            bgColor,
                            ((tran.amount!! / totalTransAmount)).toFloat()
                        )
                    )
                }

                "Withdraw" -> {
                    val bgColor: Color = MaterialTheme.colors.primaryVariant
                    entries.add(
                        PieChartEntry(
                            bgColor,
                            ((tran.amount!! / totalTransAmount)).toFloat()
                        )
                    )

                }
            }

        }
        StatementSummary(customer = myStatement.customer!!)
        Spacer(modifier = Modifier.height(20.dp))
        CustomDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text(
                text = "Type:",
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
                    value = selectedCategory.uppercase(),
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    placeholder = {
                        Text(text = "Select Type")
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
                    val categories = mutableListOf<String>()
                    fullTransactionList.forEach {
                        categories.add(it.type!!)
                    }
                    val uniqueDates = categories.toSet().toList()
                    uniqueDates.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(text = option)
                            },
                            onClick = {
                                selectedCategory = option
                                isExpanded = false
                            }
                        )
                    }
                }
            }

        }
//        Box(contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxWidth()) {
//            DataPieChart(entries)
//        }
        val pairsList = mutableMapOf<Any, Float>()
        selectedList.forEach {
            pairsList[it.category!!] = it.amount!!.toFloat()
        }
        BarChartGraph(
            data = pairsList,
            height = 250.dp,
            isExpanded = showChart,
            bottomEndRadius = 30.dp,
            bottomStartRadius = 30.dp
        ) {
            showChart = !showChart
        }
        Spacer(modifier = Modifier.height(80.dp))
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
