package com.lorenzoog.testingjetpackcompose.ui.profile

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.InnerPadding
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp

@Composable
fun ProfileBody() {
  val actions = listOf(
    "Issues",
    "Pull requests",
    "Repositories",
    "Organizations"
  )

  Column(modifier = Modifier.padding(top = 50.dp, bottom = 16.dp)) {
    Text(
      modifier = Modifier.padding(bottom = 12.dp),
      text = "My Work",
      style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = TextUnit.Em(5.5)
      )
    )

    actions.forEach { action ->
      Button(
        modifier = Modifier
          .padding(0.dp)
          .fillMaxWidth(),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        padding = InnerPadding(),
        shape = MaterialTheme.shapes.medium,
        onClick = {}
      ) {
        Text(
          modifier = Modifier
            .gravity(Alignment.Start)
            .padding(
              horizontal = 8.dp,
              vertical = 12.dp
            ),
          text = action,
          style = TextStyle(
            fontWeight = FontWeight.Normal,
            fontSize = TextUnit.Em(5)
          )
        )
      }
    }
  }
}
