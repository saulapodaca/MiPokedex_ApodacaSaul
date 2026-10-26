package apodaca.saul.mipokedex_compose_apodacasaul.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import apodaca.saul.mipokedex_compose_apodacasaul.model.data.Users

class UserViewModel(users: Users): ViewModel() {
    var loginError by mutableStateOf<Boolean>(false)
    var errorDescription by mutableStateOf<String>("")
    val users = users

    fun login(user: String, password: String){
        val user = users.loginService(user, password)
        if (user == null){
            loginError = true
            errorDescription = "Usuario o contraseña incorrecta"
        }else {
            loginError = false
            errorDescription = ""
            users.saveUser(user.email, user.name)
        }

    }

    fun isLoggedIn(): Boolean{
        return users.isLoggedIn()
    }



}