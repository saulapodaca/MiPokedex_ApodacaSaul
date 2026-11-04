package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.Users
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.User
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.USER

class UserViewModel(users: Users): ViewModel() {
    var currentUser by mutableStateOf<User?>(null)

    var grid by mutableStateOf(users.getPokemonDisplay())
        private set

    init {

        currentUser = users.getCurrentUser()
    }

    var loginError by mutableStateOf<Boolean>(false)
    var errorDescription by mutableStateOf<String>("")
    val users = users



    fun login(user: String, password: String) {
        val loggedUser = users.loginService(user, password)

        if (loggedUser == null) {
            loginError = true
            errorDescription = "Usuario o contraseña incorrecta"
        } else {
            currentUser = loggedUser
            grid = users.getPokemonDisplay()
            loginError = false
            errorDescription = ""
        }
    }

    fun isLoggedIn(): Boolean{
        return users.isLoggedIn()
    }

    fun logout() {
        users.logout()
        currentUser = null
        loginError = false
        errorDescription = ""
    }

    fun changePokemonDisplay(value: Boolean) {
        grid = value
        users.savePokemonDisplay(value)
    }

}