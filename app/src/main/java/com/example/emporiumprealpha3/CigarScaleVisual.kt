package com.example.emporiumprealpha3

import android.content.res.Configuration
import android.util.DisplayMetrics
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emporiumprealpha3.data.DemoData
import com.example.emporiumprealpha3.model.ToolBarButtonOption
import com.example.emporiumprealpha3.ui.theme.AppTheme
import com.google.android.filament.Box
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarScaleVisual(
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
                    0.2f to MaterialTheme.colorScheme.surface,
                    1f to MaterialTheme.colorScheme.background,
                    start = Offset(180f, 0f),
                    end = Offset(180f, 300f)
                )
            )
    ) {
        ToolBar(
            title = "CIGAR VISUAL",
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
                    }
                }

            if (cigar.length!=null && cigar.ringGauge!=null) {

                val inchHeight = cigar.length
                val inchWidth = cigar.ringGauge / 64

                val dpHeight = inchHeight * 160
                val dpWidth = inchWidth * 160

                Box(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(50.dp)
                ) {
                    Box(
                        Modifier
                            .align(Alignment.Center)
                            .width(dpWidth.dp)
                            .height(dpHeight.dp)
                    )
                    {
                        val painter = painterResource(id = R.drawable.cigarvisualization)
                        Image(
                            painter = painter,
                            contentDescription = "A Cigar",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.Center)
                        )
                    }
                }
            } else {
                Text(
                    text = "Cigar Measurements Not Found",
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium
                )
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
private fun CigarScaleVisualPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            CigarScaleVisual("1")
        }
    }
}