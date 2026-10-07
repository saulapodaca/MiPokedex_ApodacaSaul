package apodaca.saul.mipokedex_compose_apodacasaul.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apodaca.saul.mipokedex_compose_apodacasaul.data.getPokemonByNumber
import apodaca.saul.mipokedex_compose_apodacasaul.screens.MenuPokedexScreen
import apodaca.saul.mipokedex_compose_apodacasaul.screens.PokemonDetailScreen

@Composable
fun MyApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = PokemonList) {
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding,
                onNavigateToDetail = { id -> navController.navigate(route = PokemonDetail(id)) })
        }
        composable<PokemonDetail> {
            val pokemon = it.arguments?.getInt("pokemon") ?: -1
            PokemonDetailScreen(innerPadding, getPokemonByNumber(pokemon))
        }
    }
}