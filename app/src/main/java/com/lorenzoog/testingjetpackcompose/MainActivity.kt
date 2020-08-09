package com.lorenzoog.testingjetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.lorenzoog.testingjetpackcompose.services.GithubService
import com.lorenzoog.testingjetpackcompose.services.createRetrofit
import com.lorenzoog.testingjetpackcompose.ui.TestingJetpackComposeTheme
import com.lorenzoog.testingjetpackcompose.ui.app.App
import retrofit2.create

val retrofit = createRetrofit()
val githubService = retrofit.create<GithubService>()

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      TestingJetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          App()
        }
      }
    }
  }
}
