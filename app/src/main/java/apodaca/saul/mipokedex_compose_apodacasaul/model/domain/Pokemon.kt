package apodaca.saul.mipokedex_compose_apodacasaul.model.domain

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class Pokemon(
    val name: String,
    val number: Int,
    val type: String,
    val description: String,
    val height: Float,
    val weight: Float,
    var favorite: Boolean,
    val ability: String,
    val image: Int
) {
    companion object {
    }
}
