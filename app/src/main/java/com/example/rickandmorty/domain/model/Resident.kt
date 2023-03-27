package com.example.rickandmorty.domain.model

data class Resident(
    val id : Int,
    val name: String,
    val url: String,
    val status: String,
    val specy: String,
    val gender: String,
    val origin: String,
    val location: String,
    val episodes :List<String>,
    val createdAt : String,
    val imageUrl : String
)