package apodaca.saul.mipokedex_compose_apodacasaul.model.data

import android.content.Context
import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.User
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.ARCHIVO
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.POKEMON_DISPLAY
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.USER
import apodaca.saul.mipokedex_compose_apodacasaul.model.preferences.USERNAME

class Users(context: Context) {

    val shared = context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)
    val validUsers = listOf<User>(
        User("saul@ejemplo.com", "123456", "Saulin Pirulin"),
        User("alex@gmail.com", "123456", "Axel Garces"),
        User("potter@gmail.com", "123456", "Harry Popotter")
    )

    fun getUserByMail(mail: String): User? {
        val users = validUsers.filter { it.email.equals(mail, ignoreCase = true) }
        return if (users.isEmpty()) null else users.first()
    }

    fun loginService(user: String, contrasenia: String): User? {
        val user = getUserByMail(user)
        user?.let {
            if (user.password.equals(contrasenia)) {
                saveUser(user.email, user.name)
                return user
            }

        }
        return null
    }

    fun saveUser(user: String, name: String) {
        val editor = shared.edit()
        editor.putString(USER, user)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun logout() {
        shared.edit()
            .remove(USER)
            .remove(USERNAME)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        val user = shared.getString(USER, null)
        val userName = shared.getString(USERNAME, null)

        if (user != null && userName != null) return true

        return false

    }

    fun getCurrentUser(): User?{
        val user = shared.getString(USER, null)
        if (user != null){
            return getUserByMail(user)
        }
        return null
    }

    fun savePokemonDisplay(grid: Boolean){
        val email = shared.getString(USER, null) ?: return

        shared.edit()
            .putBoolean("${POKEMON_DISPLAY}_$email", grid)
            .apply()
    }

    fun getPokemonDisplay(): Boolean {
        val email = shared.getString(USER, null) ?: return false

        return shared.getBoolean("${POKEMON_DISPLAY}_$email", false)
    }
}