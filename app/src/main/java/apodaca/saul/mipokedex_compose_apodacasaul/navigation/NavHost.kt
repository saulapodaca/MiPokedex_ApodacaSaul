package apodaca.saul.mipokedex_compose_apodacasaul.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.MenuPokedexScreen
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.PokemonDetailScreen
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.PokemonViewModel

val PokemonViewModel = PokemonViewModel()
@Composable
fun MyApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = PokemonList) {

        //Menu Principal
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding,
                onNavigateToDetail = { id -> navController.navigate(route = PokemonDetail(id)) })
        }

        //Pantalla del detalle
        composable<PokemonDetail> {
            val pokemonNumber = it.arguments?.getInt("pokemon") ?: -1
            val pokemon = PokemonViewModel.getPokemonByNumber(pokemonNumber)

            //Esto es para que las flechitas de abajo del detalle funcionen jeje
            PokemonDetailScreen(
                innerPadding = innerPadding,
                pokemon = pokemon,
                onNavigateToDetail = { nextOrPrevId ->
                    navController.navigate(route = PokemonDetail(nextOrPrevId)) {
                        //Esto reemplaza el detalle actual para que no se apilen infinitamente
                        popUpTo<PokemonDetail> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}