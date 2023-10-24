@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.emporiumprealpha3

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.AppTheme
import com.example.emporiumprealpha3.ui.theme.DemoData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CigarCatalogueFiltersOpen(Modifier)
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarCatalogueFiltersOpen(modifier: Modifier = Modifier) {
    var showFilterPanel by remember {
        mutableStateOf(true)
    }
    var selectedBrand by remember {
        mutableStateOf("None")
    }
    var brandSearch by remember {
        mutableStateOf("")
    }
    var titleSearch by remember {
        mutableStateOf("")
    }


    val screenWidth = DisplayMetrics().widthPixels.toFloat()



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    0.2f to MaterialTheme.colorScheme.surface,
                    1f to MaterialTheme.colorScheme.background,
                    start = Offset(180f, 0f),
                    end = Offset(180f, 300f)
                )
            )
    ) {
        Row( // Toolbar
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeight(height = 60.dp)
            //.padding(vertical = 10.dp)
        ) {
            IconButton(
                onClick = { },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_24px),
                    contentDescription = "Navigation Menu",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp))
            }
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
                    text = "CIGARS",
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            val filterIconColor: Color by animateColorAsState(
                if (showFilterPanel) MaterialTheme.colorScheme.onPrimaryContainer
                else MaterialTheme.colorScheme.primary,
                label = "filterIconColorClickFade"
            )
            IconButton(
                onClick = {showFilterPanel = !showFilterPanel},
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.filter_24px),
                    contentDescription = "Filter",
                    tint = filterIconColor,
                    modifier = Modifier.size(48.dp))
            }
        }

        AnimatedVisibility(showFilterPanel) {
            Column( // Filter Panel
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Bottom),
                modifier = Modifier
                    .fillMaxWidth()
                    //.requiredHeight(height = 246.dp)
                    .wrapContentHeight(unbounded = true)
                    .padding(all = 10.dp)
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
                            if (selectedBrand == "None") MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.primaryContainer,
                            label = "filterIconColorClickFade"
                        )
                        Button(
                            onClick = { selectedBrand = "None" },
                            shape = RoundedCornerShape(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = brandNoneOptionColor
                            ),
                            contentPadding = PaddingValues(5.dp,0.dp),
                            modifier = Modifier
                                .fillMaxHeight()
                                .wrapContentWidth()
                        ) {
                            Text(
                                text = "None",
                                color = if (selectedBrand == "None") MaterialTheme.colorScheme.onPrimary
                                        else MaterialTheme.colorScheme.onPrimaryContainer,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = if (selectedBrand == "None") FontWeight.Bold
                                    else FontWeight.Normal
                                ),
                            )
                        }
                        DemoData.Brands.forEach { brand ->
                            val brandOptionColor: Color by animateColorAsState(
                                if (selectedBrand == brand) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.primaryContainer,
                                label = "filterIconColorClickFade"
                            )
                            Button(
                                onClick = { selectedBrand = brand },
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = brandOptionColor
                                ),
                                contentPadding = PaddingValues(5.dp,0.dp),
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    text = brand,
                                    color = if (selectedBrand == brand) MaterialTheme.colorScheme.onPrimary
                                    else MaterialTheme.colorScheme.onPrimaryContainer,
                                    textAlign = TextAlign.Center,
                                    style = TextStyle(
                                        fontSize = 12.sp,
                                        fontWeight = if (selectedBrand == brand) FontWeight.Bold
                                        else FontWeight.Normal,
                                    )
                                )
                            }
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
                                onValueChange = {newValue -> brandSearch = newValue},
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
                                    contentDescription = "Search Brands",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                            SimpleTextField(
                                value = titleSearch,
                                onValueChange = {newValue -> titleSearch = newValue},
                                placeholderText = "Search Brands...",
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
        Column( // Content
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
                .verticalScroll(rememberScrollState())
        )
        {
            DemoData.Cigars.forEach { cigar ->
                var componentWidth by remember { mutableStateOf(0f) }
                var componentHeight by remember { mutableStateOf(0f) }
                val density = LocalDensity.current
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 79.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .onGloballyPositioned {
                            componentWidth = with(density) {
                                it.size.width.toFloat()
                            }
                            componentHeight = with(density) {
                                it.size.height.toFloat()
                            }
                        }
                        .background(
                            brush = Brush.linearGradient(
                                0.1f to MaterialTheme.colorScheme.primary.copy(alpha = 0f),
                                0.5f to MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                                1f to MaterialTheme.colorScheme.surface,
                                start = Offset(componentWidth, 0f),
                                end = Offset(0f, componentHeight)
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(weight = 1f)
                            .padding(all = 10.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 5.dp)
                        ) {
                            Text(
                                text = cigar.Title,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = TextStyle(
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier
                                    .requiredWidth(width = 181.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(horizontal = 5.dp)
                        ) {
                            Text(
                                text = cigar.Price.toString(),
                                color = MaterialTheme.colorScheme.onSurface,
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 4.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = MaterialTheme.colorScheme.onSurface)
                            )
                            Text(
                                text = cigar.Length.toString() + "\"",
                                color = MaterialTheme.colorScheme.onSurface,
                                style = TextStyle(
                                    fontSize = 12.sp
                                )
                            )
                            Box(
                                modifier = Modifier
                                    .requiredSize(size = 4.dp)
                                    .clip(shape = CircleShape)
                                    .background(color = MaterialTheme.colorScheme.onSurface)
                            )
                            Text(
                                text = cigar.Strength,
                                color = MaterialTheme.colorScheme.onSurface,
                                style = TextStyle(
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxHeight()
                            .requiredWidth(width = 79.dp)
                            .clip(shape = RoundedCornerShape(6.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.macanudorobusto1),
                            contentDescription = "MacanudoRobusto 1",
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
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

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun CigarCatalogueFiltersOpenPreview() {
    CigarCatalogueFiltersOpen(Modifier)
}