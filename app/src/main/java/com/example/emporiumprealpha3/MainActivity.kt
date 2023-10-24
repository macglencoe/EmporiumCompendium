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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.MutableState
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
import com.example.emporiumprealpha3.model.Cigar
import com.example.emporiumprealpha3.model.ToolBarButtonOption
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
    var showMenu by remember {
        mutableStateOf(true)
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
        ToolBar(
            "CIGARS",
            ToolBarButtonOption.MENU, {showMenu = !showMenu}, showMenu,
            ToolBarButtonOption.FILTER, {showFilterPanel = !showFilterPanel}, showFilterPanel,
            Modifier
                .fillMaxWidth()
                .requiredHeight(height = 60.dp)

        )

        AnimatedVisibility(showFilterPanel) {
            FilterPanel(
                brandSearchChanged = {brandSearch = it}, brandSearch,
                titleSearchChanged = {titleSearch = it}, titleSearch,
                modifier = Modifier
                    .fillMaxWidth()
                    //.requiredHeight(height = 246.dp)
                    .wrapContentHeight(unbounded = true)
                    .padding(all = 10.dp)
            )
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
            // Filter Cigars and Display
            DemoData.Cigars.filter {
                it.title.contains(titleSearch, true) && it.brand.title.contains(brandSearch, true)
            }
                .forEach{ cigar ->
                var componentWidth by remember { mutableStateOf(0f) }
                var componentHeight by remember { mutableStateOf(0f) }
                val density = LocalDensity.current
                CigarCard(
                    cigar,
                    Modifier
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
                )
            }
        }
    }
}

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
                            contentDescription = "Search Brands",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    SimpleTextField(
                        value = titleSearch,
                        onValueChange = titleSearchChanged,
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
@Composable
fun CigarList(cigarList: List<Cigar>, modifier: Modifier) {
    var componentWidth by remember { mutableStateOf(0f) }
    var componentHeight by remember { mutableStateOf(0f) }
    val density = LocalDensity.current

    LazyColumn(modifier = modifier) {
        items(cigarList) {cigar ->
            CigarCard(
                cigar = cigar,
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
            )
        }
    }
}

@Composable
fun CigarCard(cigar: Cigar, modifier: Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
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
                    text = cigar.title,
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
                    text = cigar.price.toString(),
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
                    text = cigar.length.toString() + "\"",
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
                    text = cigar.strength,
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
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
            )
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