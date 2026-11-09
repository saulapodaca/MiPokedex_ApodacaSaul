package apodaca.saul.mipokedex_compose_apodacasaul.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.LoginScreen
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.MenuPokedexScreen
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.PokemonDetailScreen
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.PokemonHuntScreen
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.ProfileScreen
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.PokemonDetailViewModel
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.PokemonViewModel
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.UserViewModel

@Composable
fun MyApp(innerPadding: PaddingValues, userViewModel: UserViewModel,
          pokemonDetailViewModel: PokemonDetailViewModel,
          pokemonViewModel: PokemonViewModel
) {
    val navController = rememberNavController()
    val navigateToDetail = {id: Int ->navController.navigate(route = PokemonDetail(id))}
    val navigateToMenu = {
        navController.navigate(PokemonList)
    }
    val navigateToProfile = {
        navController.navigate(Profile)
    }
    NavHost(navController, startDestination = Login) {

        composable<Login> {
            LoginScreen(innerPadding,navigateToMenu, userViewModel)
        }

        //Menu Principal
        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding,
                onNavigateToDetail = { id -> navController.navigate(route = PokemonDetail(id)) },
                onNavigateToHunt = {navController.navigate(route= PokemonHunt)},
                onLogout = {
                    userViewModel.logout()
                    navController.navigate(Login){
                        popUpTo<PokemonList> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                grid = userViewModel.grid,
                onGridChange = { userViewModel.changePokemonDisplay(it) },
                navigateToProfile
                )
        }

        composable<PokemonHunt>{
            PokemonHuntScreen(innerPadding, onNavigateToDetail = navigateToDetail)
        }

        composable<Profile> {
            ProfileScreen(innerPadding, userViewModel)
        }

        //Pantalla del detalle
        composable<PokemonDetail> {
            val pokemonNumber = it.arguments?.getInt("pokemon") ?: -1

            //Esto es para que las flechitas de abajo del detalle funcionen jeje
            PokemonDetailScreen(
                innerPadding = innerPadding,
                pokemonNumber = pokemonNumber,
                onNavigateToDetail = { nextOrPrevId ->
                    navController.navigate(route = PokemonDetail(nextOrPrevId)) {
                        //Esto reemplaza el detalle actual para que no se apilen infinitamente
                        popUpTo<PokemonDetail> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                pokemonDetailViewModel,
                pokemonViewModel

            )
        }
    }
}