package com.example.emporiumprealpha3

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BrandSearchCard(
    brandName: String,
    brandSearchChanged: (String) -> Unit,
    brandSearch: String
) {
    val brandOptionColor: Color by animateColorAsState(
        if (brandSearch == brandName) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.primaryContainer,
        label = "filterIconColorClickFade"
    )
    Button(
        onClick = { brandSearchChanged(brandName) },
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = brandOptionColor
        ),
        contentPadding = PaddingValues(5.dp, 0.dp),
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth()
    ) {
        Text(
            text = brandName,
            color = if (brandSearch == brandName) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onPrimaryContainer,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = if (brandSearch == brandName) FontWeight.Bold
                else FontWeight.Normal
            ),
        )
    }
}