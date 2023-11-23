package com.pay.payanalysis.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pay.payanalysis.R

@Composable
fun WelcomeNameCard() {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(vertical = 20.dp),
    ) {
        Text(
            text = "Welcome: ",
            style = TextStyle(
                color = colorResource(id = R.color.blue_200),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        );Text(
        text = "Moses Kamira",
        style = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
        )
    );
    }
}