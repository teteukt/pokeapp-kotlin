package com.cwi.matheus.pokeapp.data.network.mapper

import com.cwi.matheus.pokeapp.data.database.entity.PokemonEntity
import com.cwi.matheus.pokeapp.data.database.entity.PokemonStatList
import com.cwi.matheus.pokeapp.data.database.entity.PokemonTypeList
import com.cwi.matheus.pokeapp.data.network.entity.PokemonResponse
import com.cwi.matheus.pokeapp.domain.entity.Pokemon
import com.cwi.matheus.pokeapp.domain.entity.PokemonStat
import com.cwi.matheus.pokeapp.domain.entity.PokemonType
import com.cwi.matheus.pokeapp.domain.entity.Stat

class PokemonMapper : DomainMapper<PokemonResponse, Pokemon> {

    fun toEntity(from: Pokemon): PokemonEntity =
        PokemonEntity(
            pokemonId = from.pokemonId,
            name = from.name,
            height = from.height,
            weight = from.weight,
            imageUrl = from.imageUrl,
            stats = PokemonStatList(from.stats),
            id = from.id,
            types = PokemonTypeList(from.types)
        )

    override fun toDomain(from: PokemonResponse): Pokemon {
        val statList = from.stats.map { PokemonStat(it.baseStat, it.baseStat, Stat(it.stat.name)) }
        val typeList = from.types.map { PokemonType(it.type.name, it.type.url) }

        return Pokemon(
            pokemonId = from.id,
            name = from.name,
            height = from.height,
            weight = from.weight,
            imageUrl = getArtworkUrlByPokemonId(from.id),
            stats = statList,
            createdAt = System.currentTimeMillis(),
            types = typeList
        )
    }


    override fun toDomain(from: List<PokemonResponse>): List<Pokemon> = from.map { toDomain(it) }

    /**
     * Estou buscando a imagem do pokemon desta forma por que a API não entrega a URL pelo endpoint,
     * no caso, eu teria que fazer duas requisições diferentes apenas para buscar a imagem.
     */
    private fun getArtworkUrlByPokemonId(id: Int): String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
}