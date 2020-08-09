package com.lorenzoog.testingjetpackcompose.services

import androidx.compose.ambientOf
import com.lorenzoog.testingjetpackcompose.model.Repo
import com.lorenzoog.testingjetpackcompose.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

val GithubService = ambientOf<IGithubService> { error("No provided github service") }

interface IGithubService {

  @GET("users/{user}/repos")
  fun findRepos(@Path("user") user: String): Call<List<Repo>>

  @GET("users/{user}")
  fun findUser(@Path("user") user: String): Call<User>

}
