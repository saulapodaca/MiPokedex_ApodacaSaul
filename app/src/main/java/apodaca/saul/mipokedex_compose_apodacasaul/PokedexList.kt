package apodaca.saul.mipokedex_compose_apodacasaul

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import apodaca.saul.mipokedex_compose_apodacasaul.view.components.MenuPokedex
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.pokemonList
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.ARCHIVO
import apodaca.saul.mipokedex_compose_apodacasaul.navigation.MyApp
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.MiPokedex_Compose_ApodacaSaulTheme
import apodaca.saul.mipokedex_compose_apodacasaul.view.screens.LoginScreen

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPokedex_Compose_ApodacaSaulTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //MyApp(innerPadding)
                    LoginScreen(innerPadding, {})
                }
            }
        }
        val sharedP = getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiPokedex_Compose_ApodacaSaulTheme {
        MenuPokedex(pokemonList, {})
    }
}