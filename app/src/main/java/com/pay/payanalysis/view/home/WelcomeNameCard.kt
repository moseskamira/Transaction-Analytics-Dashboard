package com.pay.payanalysis.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                color = colorResource(id = R.color.blue_200),
            ),
        );Text(
        text = "Moses Kamira",
        style = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp,
            color = colorResource(id = R.color.black),
        ),
    );
    }
}