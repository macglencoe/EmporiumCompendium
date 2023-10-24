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

@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun CigarCatalogueFiltersOpenPreview() {
    CigarCatalogueFiltersOpen(Modifier)
}