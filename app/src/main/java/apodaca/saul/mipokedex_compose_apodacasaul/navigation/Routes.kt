package apodaca.saul.mipokedex_compose_apodacasaul.navigation

import kotlinx.serialization.Serializable

@Serializable
object PokemonList


@Serializable
object PokemonHunt

@Serializable
data class PokemonDetail(val pokemon: Int)