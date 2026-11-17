package apodaca.saul.mipokedex_compose_apodacasaul.model.data.sqlite

import android.content.ContentValues
import android.database.Cursor
import androidx.compose.ui.Modifier
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon

class PokemonMapper {

    fun getPokemon(cursor: Cursor): Pokemon{
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_ID))
        val num = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NUMBER))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_NAME))
        val type = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_TYPE))
        val description = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_DESCRIPTION))
        val height = cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_HEIGHT))
        val weight = cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_WEIGHT))
        val ability = cursor.getString(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_ABILITY))
        val image = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_IMAGE))

        return Pokemon(
            id = id,
            number = num,
            name = name,
            type = type,
            description = description,
            height = height,
            weight = weight,
            ability = ability,
            favorite = false,
            image = image
        )
    }

    fun getContentValues(pokemon: Pokemon): ContentValues{
        val values = ContentValues().apply {
            // We omit COLUMN_ID so SQLite can auto-increment/generate it
            put(Contract.Pokemons.COLUMN_NAME_NUMBER, pokemon.number)
            put(Contract.Pokemons.COLUMN_NAME_NAME, pokemon.name)
            put(Contract.Pokemons.COLUMN_NAME_TYPE, pokemon.type)
            put(Contract.Pokemons.COLUMN_NAME_DESCRIPTION, pokemon.description)
            put(Contract.Pokemons.COLUMN_NAME_HEIGHT, pokemon.height)
            put(Contract.Pokemons.COLUMN_NAME_WEIGHT, pokemon.weight)
            put(Contract.Pokemons.COLUMN_NAME_ABILITY, pokemon.ability)
            put(Contract.Pokemons.COLUMN_NAME_IMAGE, pokemon.image)
        }
        return values
    }
}