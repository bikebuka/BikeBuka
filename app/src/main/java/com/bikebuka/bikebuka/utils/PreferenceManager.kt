package com.bikebuka.bikebuka.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.bikebuka.bikebuka.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PreferenceManager(context: Context) {
    private val dataStore = context.createDataStore(name = "preference")

    companion object {
        val USERNAME = preferencesKey<String>("username")
    }

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = user.name
        }
    }

    val user: Flow<User> = dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            when (val name = preferences[USERNAME] ?: "") {
                "" -> User("blank")
                else -> User(name)
            }
        }
}