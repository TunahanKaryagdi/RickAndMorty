package com.example.rickandmorty.data.model

import com.example.rickandmorty.domain.model.Location

data class LocationDto(
    val info: Info,
    val results: List<Result>
)


fun LocationDto.toLocation(): List<Location> {
    var list = mutableListOf<Location>()
    this.results.forEach { result ->
        list.add(
            Location(
                id = result.id,
                name = result.name
            )
        )
    }
    return list
}