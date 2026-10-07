package apodaca.saul.mipokedex_compose_apodacasaul.model.domain

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lastCapture: Pokemon? = null
)
