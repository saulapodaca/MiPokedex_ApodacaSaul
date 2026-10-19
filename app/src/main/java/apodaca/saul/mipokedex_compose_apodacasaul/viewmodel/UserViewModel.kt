package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.loginService

class UserViewModel: ViewModel() {
    var loginError by mutableStateOf<Boolean>(false)
    var errorDescription by mutableStateOf<String>("")

    fun login(user: String, password: String){
        val user = loginService(user, password)
        if (user == null){
            loginError = true
            errorDescription = "Usuario o contraseña incorrecta"
        }else {
            loginError = false
            errorDescription = ""
        }

    }
}