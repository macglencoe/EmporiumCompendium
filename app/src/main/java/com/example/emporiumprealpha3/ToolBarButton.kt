package com.example.emporiumprealpha3

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.emporiumprealpha3.model.ToolBarButtonOption

@Composable
fun ToolBarButton(
    option: ToolBarButtonOption,
    onClick: () -> Unit,
    state: Boolean,
) {
    // Set animated color of the button's icon
    val iconColor: Color by animateColorAsState(
        if (state) MaterialTheme.colorScheme.onPrimaryContainer
        else MaterialTheme.colorScheme.primary,
        label = "toolbarIconColorClickFade$option"
    )

    IconButton(
        onClick = { onClick() } // Toggle
    ) {
        if (option.iconResource != null) {
            Icon(
                painter = painterResource(id = option.iconResource), // Set icon from option
                contentDescription = option.contentDescription, // Set content description from option
                tint = iconColor,
                modifier = Modifier.size(48.dp)
            )
        }
    }
}