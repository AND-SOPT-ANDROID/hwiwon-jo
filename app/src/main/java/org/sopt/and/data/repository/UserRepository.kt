package org.sopt.and.data.repository

class UserRepository {
    var email: String? = null
    var password: String? = null

    fun saveUserData(email: String, password: String) {
        this.email = email
        this.password = password
    }

    fun getUserDate(): Pair<String?, String?> {
        return Pair(email, password)
    }

}