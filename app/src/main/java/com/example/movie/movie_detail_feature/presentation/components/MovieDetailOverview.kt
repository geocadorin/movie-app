package com.example.movie.movie_detail_feature.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.movie.R
import com.example.movie.ui.theme.white

@Composable
fun MovieDetailOverview(
    modifier: Modifier = Modifier,
    overview: String
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.description),
            color = white,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold
        )
        
        if (expanded){
            Text(
                text = overview,
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.clickable{
                    expanded = !expanded
                }
            )
        } else {
            Text(
                text = overview,
                color = Color.DarkGray,
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable{
                    expanded = !expanded
                }
            )
        }
    }
}