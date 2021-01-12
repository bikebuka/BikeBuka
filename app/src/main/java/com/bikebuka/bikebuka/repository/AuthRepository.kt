package com.bikebuka.bikebuka.repository

import com.bikebuka.bikebuka.domain.User

interface AuthRepository {
    fun phoneAuth(phone: String)

    fun saveUser(user: User)
}