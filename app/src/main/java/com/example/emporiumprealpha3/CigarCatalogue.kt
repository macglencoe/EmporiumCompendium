package com.example.emporiumprealpha3

import android.util.DisplayMetrics
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.emporiumprealpha3.model.ToolBarButtonOption
import com.example.emporiumprealpha3.ui.theme.DemoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarCatalogue(modifier: Modifier = Modifier, drawerState: DrawerState, scope: CoroutineScope) {
    var showFilterPanel by remember {
        mutableStateOf(false)
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
            ToolBarButtonOption.MENU, {
                                      scope.launch {
                                          drawerState.apply {
                                              if (isClosed) open() else close()
                                          }
                                      }
            }, showMenu,
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