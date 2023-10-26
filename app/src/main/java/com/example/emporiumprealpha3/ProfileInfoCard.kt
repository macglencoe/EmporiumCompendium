package com.example.emporiumprealpha3

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.emporiumprealpha3.data.DemoData
import com.example.emporiumprealpha3.ui.theme.AppTheme
import java.util.Dictionary
import java.util.Locale

@Composable
fun ProfileInfoCard(
    title: String,
    keyValueInfo: Map<String, String>? = null,
    deeplinkInfo: Map<String, String>? = null,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    var componentWidth by remember { mutableStateOf(0f) }
    val density = LocalDensity.current
    Column( // Card
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .onGloballyPositioned {
                componentWidth = with(density) {
                    it.size.width.toFloat()
                }
            }
            .background(
                brush = Brush.linearGradient(
                    0f to Color.Transparent,
                    1f to MaterialTheme.colorScheme.surface,
                    start = Offset(componentWidth, 0f),
                    end = Offset(0f, 0f)
                )
            )
            .padding(vertical = 10.dp)
    ) {
        Column( // Info
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row( // Title
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = title.uppercase(),
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Column( // Card Content
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                keyValueInfo?.forEach { entry ->
                    Row( // Key-Value Info
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(
                                horizontal = 10.dp,
                                vertical = 3.dp
                            )
                    ) {
                        Text(
                            text = entry.key.uppercase(),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = entry.value.uppercase(),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                }
                deeplinkInfo?.forEach { entry ->
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    ) {
                        Button(
                            { navController?.navigate(entry.value) }, // Navigate *if navController is not null*
                            modifier = Modifier
                        ) {
                            Text(
                                text = entry.key.uppercase(),
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
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
private fun ProfileInfoCardPreview() {
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProfileInfoCard(
                "Size",
                keyValueInfo = mapOf(
                    "Length" to "5\"",
                    "Ring Gauge" to "50"
                ),
                deeplinkInfo = mapOf(
                    "See Scale Visual" to "CigarCatalogue" //TODO//
                )
            )
        }
    }
}