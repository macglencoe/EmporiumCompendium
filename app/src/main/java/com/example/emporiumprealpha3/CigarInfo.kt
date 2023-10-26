package com.example.emporiumprealpha3

import android.content.res.Configuration
import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.emporiumprealpha3.data.DemoData
import com.example.emporiumprealpha3.model.Cigar
import com.example.emporiumprealpha3.model.ToolBarButtonOption
import com.example.emporiumprealpha3.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarProfile(
    cigarId: String? = null,
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
    navController: NavController? = null,
    modifier: Modifier = Modifier,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.linearGradient(
                    0.2f to androidx.compose.material3.MaterialTheme.colorScheme.surface,
                    1f to androidx.compose.material3.MaterialTheme.colorScheme.background,
                    start = Offset(180f, 0f),
                    end = Offset(180f, 300f)
                )
            )
    ) {
        ToolBar(
            title = "CIGAR",
            ToolBarButtonOption.MENU, {
                drawerScope?.launch { // Launch scope coroutine *if scope is not null*
                    drawerState?.apply { // Apply *if drawerState is not null*
                        if (isClosed) open() else close() // Toggle drawer
                    }
                }
            }, false,
            ToolBarButtonOption.BACK, {
                navController?.popBackStack() // Go back *if navController is not null*
            }, false
        )
        if(cigarId != null) {
            val cigar = DemoData.Cigars.first { it.id == cigarId }
            Row( // Cigar Title Bar
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = cigar.title.uppercase(),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    val format = DecimalFormat("$#,###.00")
                    Text(
                        text = format.format(cigar.price),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            Column( // Content
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                val sizeInfo = cigar.generateSizeInfoMap()
                if (sizeInfo.isNotEmpty()) {
                    ProfileInfoCard(
                        title = "Size",
                        keyValueInfo = sizeInfo,
                        deeplinkInfo = mapOf("See Scale Visual" to "CigarScaleVisual/$cigarId"),
                        navController = navController
                    )
                }

                val blendInfo = cigar.generateBlendInfoMap()
                if (blendInfo.isNotEmpty()) {
                    ProfileInfoCard(
                        title = "Blend",
                        keyValueInfo = blendInfo,
                    )
                }
                if (cigar.description != null) {
                    Row( // Description
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp)
                    ) {
                        Text(
                            text = cigar.description,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .requiredWidth(width = 192.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.menu_24px),
                            contentDescription = "image 1",
                            modifier = Modifier
                                .fillMaxWidth()
                                .requiredHeight(height = 116.dp)
                        )
                    }
                }
            }
        } else {
            Text(
                text = "Cigar Not Found",
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium
            )
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
private fun CigarProfilePreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CigarProfile("0")
        }
    }
}