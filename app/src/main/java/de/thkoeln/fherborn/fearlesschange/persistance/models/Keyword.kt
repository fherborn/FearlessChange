package de.thkoeln.fherborn.fearlesschange.persistance.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "keyword", indices = [(Index("id"))])
data class Keyword(
        @PrimaryKey(autoGenerate = false)
        val id: Long,
        val keyword: String
) {
    override fun toString(): String = keyword
}