package com.pay.payanalysis.view.reUsable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.pay.payanalysis.R

@Composable
fun CustomDivider(){
    Divider(
        thickness = 1.dp,
        color = colorResource(id = R.color.blue_200),
        modifier = Modifier.padding(bottom = 20.dp)
    )
}