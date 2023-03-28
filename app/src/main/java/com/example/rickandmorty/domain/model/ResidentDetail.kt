package com.example.rickandmorty.domain.model

data class ResidentDetail(
    val id: Int,
    val name: String,
    val image: String,
    val status: String,
    val specy: String,
    val gender: String,
    val origin: String,
    val location: String,
    val episodes: List<String>,
    val createdAt: String
)