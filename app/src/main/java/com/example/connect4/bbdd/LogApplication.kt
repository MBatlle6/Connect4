package com.example.connect4.bbdd

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class LogApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { LogDataBase.getDataBase(this, applicationScope) }
    val repository by lazy { LogRepository(database.dao()) }
}
