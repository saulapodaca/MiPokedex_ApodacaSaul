package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.DarkGray
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Ghost
import apodaca.saul.mipokedex_compose_apodacasaul.ui.theme.Typography
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    innerPadding: PaddingValues,
    //userViewModel: UserViewModel = viewModel()
){
    val nombre = "Saul Apodaca"
        //if(userViewModel.currentUser != null) "${userViewModel.currentUser!!.name}" else "Nombre Usuario"
    val mail = "Saul@ejemplo.com"
        //if(userViewModel.currentUser != null) "${userViewModel.currentUser!!.email}" else "Correo Electronico"
    Column(
        Modifier.fillMaxSize()
            .padding(innerPadding)
            .padding(15.dp)
    ) {
        Text(nombre,
            style = Typography.displayLarge, color = Ghost
        )

        Text(mail)

        Text("Mi compañero", style = Typography.titleLarge)
    }
}

@Preview (showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(PaddingValues())
}