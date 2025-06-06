package com.alunos_rm552323_rm552233


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAllEvents(): Flow<List<Event>>

    @Insert
    suspend fun insert(event: Event): Unit

    @Delete
    suspend fun delete(event: Event): Unit
}
