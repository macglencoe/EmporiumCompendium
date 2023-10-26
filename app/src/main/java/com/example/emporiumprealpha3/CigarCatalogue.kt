package com.example.emporiumprealpha3

import android.content.res.Configuration
import android.util.DisplayMetrics
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emporiumprealpha3.model.ToolBarButtonOption
import com.example.emporiumprealpha3.data.DemoData
import com.example.emporiumprealpha3.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarCatalogue(
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
    navController: NavController? = null,
    modifier: Modifier = Modifier,
) {
    var showFilterPanel by remember {
        mutableStateOf(false)
    }
    var brandSearch by remember {
        mutableStateOf("")
    }
    var titleSearch by remember {
        mutableStateOf("")
    }


    val screenWidth = DisplayMetrics().widthPixels.toFloat()


    val gradientEnd: Float by animateFloatAsState(
        if (showFilterPanel) 1000f else 300f,
        label = "gradientMove",
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0.2f to MaterialTheme.colorScheme.surface,
                    1f to MaterialTheme.colorScheme.background,
                    start = Offset(
                        180f, 0f
                    ),
                    end = Offset(
                        180f, gradientEnd
                    )
                )
            )
    ) {
        val ksmanPosition: Dp by animateDpAsState(
            if (showFilterPanel) 10.dp else -25.dp,
            label = "ksmanMove",
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        val ksmanBlur: Dp by animateDpAsState(
            if (showFilterPanel) 25.dp else 0.dp,
            label = "ksmanBlur",
            animationSpec = spring(stiffness = Spring.StiffnessLow)
        )
        Image(
            painter = painterResource(id = R.drawable.ksmantransparentbw),
            contentDescription = "Background Image",
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopCenter,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surface, BlendMode.Modulate),
            modifier = Modifier
                .fillMaxSize()
                .blur(ksmanBlur)
                .offset(0.dp, ksmanPosition)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()

        ) {
            ToolBar(
                "CIGARS",
                ToolBarButtonOption.MENU, {
                    drawerScope?.launch { // Launch scope coroutine *if scope is not null*
                        drawerState?.apply { // Apply *if drawerState is not null*
                            if (isClosed) open() else close() // Toggle drawer
                        }
                    }
                }, false,
                ToolBarButtonOption.FILTER, {
                    showFilterPanel = !showFilterPanel
                }, showFilterPanel
            )

            AnimatedVisibility(showFilterPanel) {
                FilterPanel(
                    brandSearchChanged = { brandSearch = it }, brandSearch,
                    titleSearchChanged = { titleSearch = it }, titleSearch,
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
                    it.title.contains(titleSearch, true) && it.brand.title.contains(
                        brandSearch,
                        true
                    )
                }
                    .forEach { cigar ->
                        var componentWidth by remember { mutableStateOf(0f) }
                        var componentHeight by remember { mutableStateOf(0f) }
                        val density = LocalDensity.current
                        CigarCard(
                            cigar,
                            { navController?.navigate("CigarProfile/" + cigar.id) },
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
                                        0.9f to MaterialTheme.colorScheme.surface,
                                        start = Offset(componentWidth, 0f),
                                        end = Offset(0f, componentHeight)
                                    )
                                )
                        )
                    }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    widthDp = 360, heightDp = 800,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CigarCataloguePreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CigarCatalogue()
        }
    }
}