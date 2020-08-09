package com.lorenzoog.testingjetpackcompose.ui.app

import androidx.compose.*
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.lorenzoog.testingjetpackcompose.model.User
import com.lorenzoog.testingjetpackcompose.services.GithubService
import com.lorenzoog.testingjetpackcompose.ui.TestingJetpackComposeTheme
import com.lorenzoog.testingjetpackcompose.ui.profile.Profile
import kotlinx.coroutines.*

private const val GITHUB_USERNAME = "LorenzooG"

@Composable
fun App() {
  var user: User? by state<User?> { null }

  val githubService = GithubService.current

  onCommit {
    val userDeferred = GlobalScope.async(Dispatchers.IO) {
      githubService.findUser(GITHUB_USERNAME)
        .execute()
        .body()
    }

    runBlocking {
      user = userDeferred.await().also(::println)
    }
  }

  Column(modifier = Modifier.padding(20.dp)) {
    if(user == null)  {
      Text("Loading the user!")
    } else {
      Profile(user!!)
    }
  }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  TestingJetpackComposeTheme {
    App()
  }
}
