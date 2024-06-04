package com.example.connect4.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import kotlinx.coroutines.flow.Flow

private const val GAME_PREFERENCES_NAME = "game_preferences"
lateinit var settingsDataStore: SettingsDataStore

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = GAME_PREFERENCES_NAME
)

class SettingsDataStore(context: Context) {

    private val GRID_SIZE = intPreferencesKey("grid_size")
    private val ALIAS = stringPreferencesKey("alias")
    private val TIME_CONTROL = booleanPreferencesKey("time_control")

    suspend fun saveTimeControlToPreferencesStore(timeControl: Boolean, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[TIME_CONTROL] = timeControl
        }
    }

    suspend fun saveAliasToPreferencesStore(alias: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[ALIAS] = alias
        }
    }

    suspend fun saveGridSizeToPreferencesStore(gridSize: Int, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[GRID_SIZE] = gridSize
        }
    }

    val preferenceFlowTimeControl: Flow<Boolean> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[TIME_CONTROL] ?: true
        }

    val preferenceFlowGridSize: Flow<Int> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[GRID_SIZE] ?: 5
        }

    val preferenceFlowAlias: Flow<String> = context.dataStore.data
        .catch {
            if (it is IOException) {
                it.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[ALIAS] ?: "Player1"
        }

}