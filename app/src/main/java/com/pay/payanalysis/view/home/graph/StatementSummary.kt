package com.pay.payanalysis.view.home.graph

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Customer

@Composable
fun StatementSummary(customer: Customer) {
    Row(horizontalArrangement = Arrangement.Start) {
        Text(
            text = "Full Name: ",
            style = TextStyle(
                color = colorResource(id = R.color.blue_200),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "${customer.name}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )
    }
    Row {
        Text(
            text = "Age: ",
            style = TextStyle(
                color = colorResource(id = R.color.blue_200),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "${customer.age}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )
    }
    Row {
        Text(
            text = "Gender: ",
            style = TextStyle(
                color = colorResource(id = R.color.blue_200),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "${customer.gender}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )
    }
    Row {
        Text(
            text = "Address: ",
            style = TextStyle(
                color = colorResource(id = R.color.blue_200),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "${customer.address}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )
    }
    Row {
        Text(
            text = "Phone: ",
            style = TextStyle(
                color = colorResource(id = R.color.blue_200),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "${customer.phone}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )
    }
    Row {
        Text(
            text = "Email: ",
            style = TextStyle(
                color = colorResource(id = R.color.blue_200),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "${customer.email}",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )
    }
}