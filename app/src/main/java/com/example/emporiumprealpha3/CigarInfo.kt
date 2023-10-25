package com.example.emporiumprealpha3

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.emporiumprealpha3.model.ToolBarButtonOption
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CigarProfile(modifier: Modifier = Modifier,
                 drawerState: DrawerState,
                 scope: CoroutineScope,
                 navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(width = 360.dp)
            .requiredHeight(height = 800.dp)
            .background(color = Color(0xff121212))
    ) {
        ToolBar(
            title = "CIGAR",
            option1 = ToolBarButtonOption.MENU,
            option1OnClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            },
            option1State = false,
            option2 = ToolBarButtonOption.BACK,
            option2OnClick = { navController.popBackStack() },
            option2State = false
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        0.48f to Color(0xff202221),
                        1f to Color(0xff1e1e1e),
                        start = Offset(180f, 0f),
                        end = Offset(180f, 126f)
                    )
                )
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "MACANUDO 1968 ROBUSTO",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2)
                Text(
                    text = "$13.00",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold))
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color(0xff202221),
                            1f to Color(0xff202221),
                            start = Offset(0f, 85f),
                            end = Offset(340f, 85f)
                        )
                    )
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "SIZE",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold))
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(color = Color(0xff202221))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 3.dp
                                )
                        ) {
                            Text(
                                text = "LENGTH",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp))
                            Text(
                                text = "5”",
                                color = Color.White,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 16.sp),
                                modifier = Modifier
                                    .fillMaxWidth())
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(color = Color(0xff202221))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 3.dp
                                )
                        ) {
                            Text(
                                text = "RING GAUGE:",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp))
                            Text(
                                text = "50",
                                color = Color.White,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 16.sp),
                                modifier = Modifier
                                    .fillMaxWidth())
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                        ) {
                            Text(
                                text = "SEE SCALE VISUAL",
                                color = Color.White,
                                textDecoration = TextDecoration.Underline,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium))
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.linearGradient(
                            0f to Color(0xff202221),
                            1f to Color(0xff202221),
                            start = Offset(0f, 173f),
                            end = Offset(340f, 173f)
                        )
                    )
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                    ) {
                        Text(
                            text = "BLEND",
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold))
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(color = Color(0xff202221))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 3.dp
                                )
                        ) {
                            Text(
                                text = "WRAPPER:",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp))
                            Text(
                                text = "HONDURAN OLANCHO SAN AGUSTIN",
                                color = Color.White,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 16.sp),
                                modifier = Modifier
                                    .fillMaxWidth())
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(color = Color(0xff202221))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 3.dp
                                )
                        ) {
                            Text(
                                text = "BINDER:",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp))
                            Text(
                                text = "CONNECTICUT HABANO",
                                color = Color.White,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 16.sp),
                                modifier = Modifier
                                    .fillMaxWidth())
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(color = Color(0xff202221))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 3.dp
                                )
                        ) {
                            Text(
                                text = "FILLER:",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp))
                            Text(
                                text = "DOMINICAN,\nNICARAGUAN ESTELI,\nNICARAGUAN OMETEPE",
                                color = Color.White,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 16.sp),
                                modifier = Modifier
                                    .fillMaxWidth())
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(color = Color(0xff202221))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 3.dp
                                )
                        ) {
                            Text(
                                text = "BODY:",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp))
                            Text(
                                text = "MEDIUM - FULL",
                                color = Color.White,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 16.sp),
                                modifier = Modifier
                                    .fillMaxWidth())
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp))
                                .background(color = Color(0xff202221))
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 3.dp
                                )
                        ) {
                            Text(
                                text = "TASTING NOTES:",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp))
                            Text(
                                text = "PEPPER, COCOA, CEDAR, COFFEE",
                                color = Color.White,
                                textAlign = TextAlign.End,
                                style = TextStyle(
                                    fontSize = 16.sp),
                                modifier = Modifier
                                    .fillMaxWidth())
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
            ) {
                Text(
                    text = "Crafted in celebration of Macanudo’s iconic legacy, Macanudo 1968 leaves expectations behind by providing a full, flavorful and intricate smoke for fans of complex cigars. But don’t let the new approach fool you, 1968 still delivers the intricately balanced flavor and inherent sweetness present in all of our cigars from the rich soils in which they are grown. The result is a cigar that has received unanimous critical acclaim with 90+ ratings from Cigar Aficionado and Cigar Insider.\n",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 12.sp),
                    modifier = Modifier
                        .requiredWidth(width = 192.dp))
                Image(
                    painter = painterResource(id = R.drawable.menu_24px),
                    contentDescription = "image 1",
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(height = 116.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(widthDp = 360, heightDp = 800)
@Composable
private fun CigarProfilePreview() {
    //CigarProfile(Modifier)
}