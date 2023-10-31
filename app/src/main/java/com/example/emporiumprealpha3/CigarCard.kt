package com.example.emporiumprealpha3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.emporiumprealpha3.model.Cigar

@Composable
fun CigarCard(
    cigar: Cigar,
    onClick: () -> Unit,
    modifier: Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {onClick()}
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
                    text = cigar.strength?:"Unknown",
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
                painter = rememberAsyncImagePainter(cigar.img_src),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}
