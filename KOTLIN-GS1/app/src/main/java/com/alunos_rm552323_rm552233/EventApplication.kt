package com.alunos_rm552323_rm552233


import android.app.Application

class EventApplication : Application() {
    val database by lazy { EventDatabase.getDatabase(this) }
    val repository by lazy { EventRepository(database.eventDao()) }
}
