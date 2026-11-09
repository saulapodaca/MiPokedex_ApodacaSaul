package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.CompanionPreferences
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.Users
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.Pokemon
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.User
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.PokemonSerializer
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.USER
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class UserViewModel(
    users: Users,
    private val companionDataStore: CompanionPreferences): ViewModel() {
    var currentUser by mutableStateOf<User?>(null)

    var grid by mutableStateOf(users.getPokemonDisplay())
        private set

    init {

        currentUser = users.getCurrentUser()
    }

    var loginError by mutableStateOf<Boolean>(false)
    var errorDescription by mutableStateOf<String>("")
    val companion: StateFlow<Pokemon> = companionDataStore.pokemonCompanion()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PokemonSerializer.defaultValue
        )
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
            users.saveUser(loggedUser.email, loggedUser.name)
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