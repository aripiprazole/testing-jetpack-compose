package com.lorenzoog.testingjetpackcompose.model

import com.google.gson.annotations.SerializedName

data class User(
  val login: String,
  @SerializedName("avatar_url")
  val avatarUrl: String
)
