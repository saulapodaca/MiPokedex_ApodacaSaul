package apodaca.saul.mipokedex_compose_apodacasaul.model.data.sqlite

import android.content.ContentValues
import android.database.Cursor
import apodaca.saul.mipokedex_compose_apodacasaul.R
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
        val image = cursor.getBlob(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_IMAGE))
        val fav = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.Pokemons.COLUMN_NAME_FAVORITE))

        return Pokemon(
            id = id,
            number = num,
            name = name,
            type = type,
            description = description,
            height = height,
            weight = weight,
            ability = ability,
            favorite = fav != 0,
            image = R.drawable.wtp,
            sqlImage = image
        )
    }

    fun getContentValues(pokemon: Pokemon): ContentValues{
        val values = ContentValues().apply {
            put(Contract.Pokemons.COLUMN_NAME_NUMBER, pokemon.number)
            put(Contract.Pokemons.COLUMN_NAME_NAME, pokemon.name)
            put(Contract.Pokemons.COLUMN_NAME_TYPE, pokemon.type)
            put(Contract.Pokemons.COLUMN_NAME_DESCRIPTION, pokemon.description)
            put(Contract.Pokemons.COLUMN_NAME_HEIGHT, pokemon.height)
            put(Contract.Pokemons.COLUMN_NAME_WEIGHT, pokemon.weight)
            put(Contract.Pokemons.COLUMN_NAME_ABILITY, pokemon.ability)
            put(Contract.Pokemons.COLUMN_NAME_IMAGE, pokemon.image)
            put(Contract.Pokemons.COLUMN_NAME_FAVORITE, pokemon.favorite)
        }
        return values
    }
}