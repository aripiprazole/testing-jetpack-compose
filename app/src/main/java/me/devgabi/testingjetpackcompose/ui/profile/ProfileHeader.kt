package me.devgabi.testingjetpackcompose.ui.profile

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.core.tag
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Brush
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import me.devgabi.testingjetpackcompose.model.User
import me.devgabi.testingjetpackcompose.ui.purple500
import me.devgabi.testingjetpackcompose.util.CoilImage

@Composable
fun ProfileHeader(user: User) {
  Row {
    ConstraintLayout(
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp),
      constraintSet = ConstraintSet {
        tag("create-issue").apply {
          right constrainTo parent.right
          top constrainTo parent.top
          bottom constrainTo parent.bottom
        }

        val userImage = tag("user-image").apply {
          left constrainTo parent.left
          top constrainTo parent.top
          bottom constrainTo parent.bottom
        }

        tag("title").apply {
          left constrainTo userImage.right
          top constrainTo parent.top
          bottom constrainTo parent.bottom
        }
      }
    ) {
      Box(shape = RoundedCornerShape(50)) {
        CoilImage(
          modifier = Modifier
            .tag("user-image")
            .drawBorder(Border(1.dp, Color.Black), RoundedCornerShape(50))
            .size(48.dp),
          model = user.avatarUrl
        )
      }

      Text(
        modifier = Modifier
          .tag("title")
          .padding(start = 16.dp),
        text = "Hello ${user.login}",
        style = TextStyle(
          fontWeight = FontWeight.Bold,

          fontSize = TextUnit.Em(7)
        )
      )

      Button(
        modifier = Modifier
          .tag("create-issue")
          .size(40.dp)
          .drawBorder(Border(2.dp, purple500), shape = CircleShape),
        backgroundColor = Color.Transparent,
        shape = CircleShape,
        contentColor = purple500,
        onClick = {}
      ) {
        Text("+")
      }
    }
  }
}
