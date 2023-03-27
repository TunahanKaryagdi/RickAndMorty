package com.example.rickandmorty.data.model

import com.example.rickandmorty.domain.model.Location

data class LocationDto(
    val info: InfoDto,
    val results: List<ResultDto>
)


fun LocationDto.toLocation(): List<Location> {
    var list = mutableListOf<Location>()
    this.results.forEach { result ->
        list.add(
            Location(
                id = result.id,
                name = result.name,
                residents = result.residents
            )
        )
    }
    return list
}