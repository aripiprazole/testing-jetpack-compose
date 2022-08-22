package me.devgabi.testingjetpackcompose.ui.profile

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.ui.core.Modifier
import androidx.ui.layout.*
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import me.devgabi.testingjetpackcompose.model.User
import me.devgabi.testingjetpackcompose.ui.TestingJetpackComposeTheme

@Composable
fun Profile(user: User) {
  Column(modifier = Modifier.padding(8.dp)) {
    ProfileHeader(user)
    ProfileBody()
    ProfileFavorites()
  }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
  TestingJetpackComposeTheme {
    Profile(
      user = User(
        login = "LorenzooG",
        avatarUrl = "https://avatars3.githubusercontent.com/u/51281661?s=460&u=8bdad7d72375dce1d24f0a3626552962af800c84&v=4"
      )
    )
  }
}
