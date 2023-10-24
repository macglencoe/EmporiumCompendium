package com.example.emporiumprealpha3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emporiumprealpha3.model.ToolBarButtonOption

@Composable
fun ToolBar(
    title: String,
    option1: ToolBarButtonOption, option1OnClick: () -> Unit, option1State: Boolean,
    option2: ToolBarButtonOption, option2OnClick: () -> Unit, option2State: Boolean,
    modifier: Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        ToolBarButton(
            option = option1,
            onClick = option1OnClick,
            state = option1State)
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                10.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .weight(weight = 1f)
        ) {
            Text(
                text = title, // Set Title text
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        ToolBarButton(
            option = option2,
            onClick = option2OnClick,
            state = option2State)
    }

}