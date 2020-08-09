package com.lorenzoog.testingjetpackcompose.services

import com.lorenzoog.testingjetpackcompose.model.Repo
import com.lorenzoog.testingjetpackcompose.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

  @GET("users/{user}/repos")
  fun findRepos(@Path("user") user: String): Call<List<Repo>>

  @GET("users/{user}")
  fun findUser(@Path("user") user: String): Call<User>

}
