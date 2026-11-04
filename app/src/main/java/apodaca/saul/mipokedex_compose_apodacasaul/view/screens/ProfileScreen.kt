package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import apodaca.saul.mipokedex_compose_apodacasaul.R
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.pokemonList
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.DarkGray
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Ghost
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Typography
import apodaca.saul.mipokedex_compose_apodacasaul.utilities.getColorType
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.UserViewModel
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.collectAsState

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel = viewModel()
){
    val nombre = if(userViewModel.currentUser != null) "${userViewModel.currentUser!!.name}" else "Nombre Usuario"
    val mail = if(userViewModel.currentUser != null) "${userViewModel.currentUser!!.email}" else "Correo Electronico"
    val pokemon = if(userViewModel.currentUser != null) userViewModel.companion.collectAsState().value else null
    Column(
        Modifier.fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(nombre,
            style = Typography.displayLarge, color = Ghost
        )

        Text(mail)

        Spacer(Modifier.size(50.dp))
        Text("Mi compañero", style = Typography.titleLarge)

        if (pokemon != null){
            Image(
                painter = painterResource(pokemon.image),
                contentDescription = "${pokemon.name} image"
            )
            Text(
                pokemon.name,
                style = Typography.labelLarge,
                color = getColorType(pokemon.type).first
            )
        } else {
            Text("No selected pokemon", style=  Typography.labelLarge)
        }


    }
}

@Preview (showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(PaddingValues())
}