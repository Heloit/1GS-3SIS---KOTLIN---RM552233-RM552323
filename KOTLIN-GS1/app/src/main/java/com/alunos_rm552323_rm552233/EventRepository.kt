package com.alunos_rm552323_rm552233

import kotlinx.coroutines.flow.Flow

class EventRepository(private val eventDao: EventDao) {
    val allEvents: Flow<List<Event>> = eventDao.getAllEvents()

    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }

    suspend fun delete(event: Event) {
        eventDao.delete(event)
    }
}