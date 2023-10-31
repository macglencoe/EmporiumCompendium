package com.example.emporiumprealpha3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.emporiumprealpha3.model.ToolBarButtonOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarImagePage(
    cigarId: String?,
    drawerState: DrawerState? = null,
    drawerScope: CoroutineScope? = null,
    navController: NavController? = null,
    modifier: Modifier = Modifier,
) {
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
                        180f, 300f
                    )
                )
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
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

            val cigar = getCigarList(LocalContext.current).first { it.id == cigarId }

            Box(
                Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = cigar.img_src
                    ),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .defaultMinSize(116.dp, 116.dp)
                        .fillMaxSize()
                        .aspectRatio(1f, false)
                        .clip(shape = RoundedCornerShape(6.dp))
                )
            }
        }
    }

}