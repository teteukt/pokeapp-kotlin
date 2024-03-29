package com.cwi.matheus.pokeapp.data.database.mapper

import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.data.network.mapper.DomainMapper
import com.cwi.matheus.pokeapp.domain.entity.Pokemon

class PokemonEntityMapper : DomainMapper<PokemonEntity, Pokemon> {

    override fun toDomain(from: PokemonEntity): Pokemon =
        Pokemon(
            id = from.id!!,
            name = from.name,
            height = from.height,
            weight = from.weight,
            imageUrl = from.imageUrl,
            stats = from.stats.stats,
            createdAt = from.createdAt,
            pokemonId = from.pokemonId,
            types = from.types.types
        )

    override fun toDomain(from: List<PokemonEntity>): List<Pokemon> =
        from.map { toDomain(it) }
}