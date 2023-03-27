package com.example.rickandmorty.data.model

import com.example.rickandmorty.domain.model.Resident

data class ResidentDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: AddressDto,
    val name: String,
    val origin: OriginDto,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)


fun ResidentDto.toResident() : Resident{
    return Resident(
        id = this.id,
        name = this.name,
        url = this.url,
        status = this.status,
        specy = this.species,
        gender = this.gender,
        origin = this.origin.name,
        location = this.location.name,
        episodes = this.episode,
        createdAt = this.created,
        imageUrl = this.image
    )
}