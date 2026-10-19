package apodaca.saul.mipokedex_compose_apodacasaul.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import apodaca.saul.mipokedex_compose_apodacasaul.viewmodel.UserViewModel

@Composable
fun LoginScreen(
    innerPadding: PaddingValues, onNavigateToMenu: () -> Unit,
    setSuccessfulLogin: (username: String, mail: String) -> String,
    viewModel: UserViewModel = viewModel()
) {

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column() {
        TextField(
            value = user,
            label = {
                Text("Correo Electrónico")
            },
            onValueChange = { it -> user = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextField(
            value = password, label = {
                Text("Contraseña")
            }, onValueChange = { it -> password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = {}) {
            Text("Iniciar Sesión")
        }
    }
}