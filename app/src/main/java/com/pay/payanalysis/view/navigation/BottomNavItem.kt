package com.pay.payanalysis.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {

    object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    object Settings : BottomNavItem("Settings", Icons.Default.Settings, "settings")
    object Account : BottomNavItem("Account", Icons.Default.AccountBox, "account")
}
