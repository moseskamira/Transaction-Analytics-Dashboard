package com.pay.payanalysis.view.home.graphTab

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.pay.payanalysis.R
import com.pay.payanalysis.model.Customer
import com.pay.payanalysis.ui.theme.Typography

@Composable
fun StatementSummary(customer: Customer) {
    Row {
        Text(
            text = "Age: ",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.blue_200),
        )
        Text(
            text = "${customer.age}",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.black)
        )
    }
    Row {
        Text(
            text = "Gender: ",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.blue_200),
        )
        Text(
            text = "${customer.gender}",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.black)
        )
    }
    Row {
        Text(
            text = "Address: ",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.blue_200),
        )
        Text(
            text = "${customer.address}",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.black)
        )
    }
    Row {
        Text(
            text = "Phone: ",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.blue_200),
        )
        Text(
            text = "${customer.phone}",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.black)
        )
    }
    Row {
        Text(
            text = "Email: ",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.blue_200),
        )
        Text(
            text = "${customer.email}",
            style = Typography.bodyLarge,
            color = colorResource(id = R.color.black)
        )
    }
}