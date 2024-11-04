package com.faiqaryadewangga.newsapp_coil.components

import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import com.faiqaryadewangga.newsapp_coil.R
import com.faiqaryadewangga.newsapp_coil.data.model.NavigationBarItem

@Composable
fun AppNavigationBar(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    isShowNavbar: Boolean,
    items: List<NavigationBarItem>,
    currentDestination: NavDestination?
) {
    if (isShowNavbar) {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = modifier
        ) {
            items.forEach { item ->

                val isSelected = currentDestination?.route == item.route

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        onClick(item.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = null,
                            tint = if (isSelected) Color(0xFF283C73) else Color.LightGray,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        val textColor = if (isSelected) Color(0xFF283C73) else Color.LightGray

                        if (item.title != null)
                            Text(
                                text = item.title,
                                color = textColor,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF283C73),
                                )
                            )
                    },
                    unselectedContentColor = Color.LightGray,
                    selectedContentColor = Color(0xFF283C73)
                )
            }
        }
    }
}