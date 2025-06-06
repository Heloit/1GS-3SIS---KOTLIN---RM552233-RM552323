package com.alunos_rm552323_rm552233


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val location: String,
    val eventType: String,
    val impact: String,
    val date: String,
    val peopleAffected: Int
)