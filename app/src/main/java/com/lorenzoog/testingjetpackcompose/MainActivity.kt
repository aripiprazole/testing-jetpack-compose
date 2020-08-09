package com.lorenzoog.testingjetpackcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Providers
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import com.lorenzoog.testingjetpackcompose.services.GithubService
import com.lorenzoog.testingjetpackcompose.services.IGithubService
import com.lorenzoog.testingjetpackcompose.ui.TestingJetpackComposeTheme
import com.lorenzoog.testingjetpackcompose.ui.app.App
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class MainActivity : AppCompatActivity() {

  private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  private val githubService = retrofit.create<IGithubService>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      TestingJetpackComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Providers(GithubService provides githubService) {
            App()
          }
        }
      }
    }
  }
}
