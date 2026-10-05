package apodaca.saul.mipokedex_compose_apodacasaul.utilities

import androidx.compose.ui.graphics.Color
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Bug
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.DarkGray
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Electric
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Fairy
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Fight
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Fire
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Flying
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Ghost
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Grass
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Ground
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Normal
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.OffWhite
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Poison
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Psych
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Rock
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Water

fun getColorType(type: String): Pair<Color, Color>{
    return when (type.trim().lowercase()){
        "normal" -> Pair(Normal, OffWhite)
        "water" -> Pair(Water, OffWhite)
        "fire" -> Pair(Fire, OffWhite)
        "psych" -> Pair(Psych, OffWhite)
        "ghost" -> Pair(Ghost, OffWhite)
        "bug" -> Pair(Bug, OffWhite)
        "poison" -> Pair(Poison, OffWhite)
        "grass" -> Pair(Grass, OffWhite)
        "ground" -> Pair(Ground, OffWhite)
        "rock" -> Pair(Rock, OffWhite)
        "electric" -> Pair(Electric, DarkGray)
        "fairy" -> Pair(Fairy, DarkGray)
        "fight" -> Pair(Fight, DarkGray)
        "flying" -> Pair(Flying, DarkGray)
        else -> Pair(OffWhite, DarkGray)
    }
}