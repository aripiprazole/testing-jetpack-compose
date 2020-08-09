package com.lorenzoog.testingjetpackcompose.ui.profile

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.drawShadow
import androidx.ui.foundation.Border
import androidx.ui.foundation.Text
import androidx.ui.foundation.drawBackground
import androidx.ui.foundation.drawBorder
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import com.lorenzoog.testingjetpackcompose.ui.purple500
import java.util.*

@Composable
fun ProfileFavorites() {
  Column(
    modifier = Modifier
      .drawBorder(Border(1.dp, Color(0xFF9F9F9F)))
      .padding(16.dp)
  ) {
    Text(
      text = "Favorites",
      style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = TextUnit.Em(5.5)
      )
    )

    Text(
      modifier = Modifier.gravity(Alignment.CenterHorizontally),
      text = "Add favorite repositories for quick access at time, without having to search"
    )

    Button(
      modifier = Modifier
        .padding(8.dp)
        .gravity(Alignment.CenterHorizontally)
        .drawBorder(Border(2.dp, purple500)),
      backgroundColor = Color.Transparent,
      contentColor = purple500,
      onClick = {}
    ) {
      Text("Add favorites".toUpperCase(Locale.ROOT))
    }
  }
}
