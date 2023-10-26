package com.example.emporiumprealpha3

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emporiumprealpha3.data.DemoData

@Composable
fun FilterPanel(
    brandSearchChanged: (String) -> Unit, brandSearch: String,
    titleSearchChanged: (String) -> Unit, titleSearch: String,
    modifier: Modifier
) {
    Column( // Filter Panel
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Bottom),
        modifier = modifier
    ) {
        Column( // Brands
            modifier = Modifier
                .fillMaxWidth()
            //.wrapContentHeight(unbounded = true)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "BRANDS",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 15.sp
                    )
                )
            }
            Row( // Brand Options
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 25.dp)
                    .padding(horizontal = 10.dp)
                    .horizontalScroll(rememberScrollState())
            ) {
                // 'None' Brand Option
                val brandNoneOptionColor: Color by animateColorAsState(
                    if (brandSearch == "") MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primaryContainer,
                    label = "filterIconColorClickFade"
                )
                Button(
                    onClick = { brandSearchChanged("") },
                    shape = RoundedCornerShape(6.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = brandNoneOptionColor
                    ),
                    contentPadding = PaddingValues(5.dp, 0.dp),
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth()
                ) {
                    Text(
                        text = "None",
                        color = if (brandSearch == "") MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = if (brandSearch == "") FontWeight.Bold
                            else FontWeight.Normal
                        ),
                    )
                }
                DemoData.Brands.forEach { brand ->
                    BrandSearchCard(
                        brandName = brand.title,
                        brandSearchChanged = {brandSearchChanged(it)},
                        brandSearch = brandSearch)
                }
            }
            Column( // Brands Search
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer
                                .copy(alpha = 0.5f)
                        )
                        .padding(
                            horizontal = 5.dp,
                            vertical = 2.dp
                        )
                ) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(25.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_24px),
                            contentDescription = "Search Brands",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    SimpleTextField(
                        value = brandSearch,
                        onValueChange = brandSearchChanged,
                        placeholderText = "Search Brands...",
                        textStyle = TextStyle.Default.copy(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        }

        Column( // Title
            modifier = Modifier
                .fillMaxWidth()
            //.wrapContentHeight(unbounded = true)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = "TITLE",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 15.sp
                    )
                )
            }
            Column( // Title Search
                verticalArrangement = Arrangement.spacedBy(
                    10.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 10.dp,
                        vertical = 5.dp
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        Alignment.Start
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(6.dp))
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer
                                .copy(alpha = 0.5f)
                        )
                        .padding(
                            horizontal = 9.dp,
                            vertical = 5.dp
                        )
                ) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(25.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_24px),
                            contentDescription = "Search Cigars",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    SimpleTextField(
                        value = titleSearch,
                        onValueChange = titleSearchChanged,
                        placeholderText = "Search Cigars...",
                        textStyle = TextStyle.Default.copy(
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    placeholderText: String = "",
    fontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    cursorBrush: Brush = SolidColor(Color.Black),
) {
    BasicTextField(modifier = modifier
        .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        maxLines = maxLines,
        enabled = enabled,
        readOnly = readOnly,
        interactionSource = interactionSource,
        textStyle = textStyle,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        onTextLayout = onTextLayout,
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (value.isEmpty()) Text(
                        placeholderText,
                        style = textStyle
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}