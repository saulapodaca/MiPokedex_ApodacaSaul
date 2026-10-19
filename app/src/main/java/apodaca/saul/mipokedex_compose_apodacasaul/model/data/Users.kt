package apodaca.saul.mipokedex_compose_apodacasaul.model.data

import apodaca.saul.mipokedex_compose_apodacasaul.model.domain.User

val validUsers = listOf<User>(
    User("saul@ejemplo.com", "123456", "Saulin Pirulin"),
    User("alex@gmail.com", "123456", "Axel Garces"),
    User("potter@gmail.com", "123456", "Harry Popotter")
);

fun getUserByMail(mail: String): User?{
    val users =  validUsers.filter { it.email.equals(mail, ignoreCase = true) }
    return if(users.isEmpty()) null else users.first()
}

fun loginService(user: String, contrasenia: String): User?{
    val user = getUserByMail(user)
    user?.let {
        if (user.password.equals(contrasenia)) return user
    }
    return null
}