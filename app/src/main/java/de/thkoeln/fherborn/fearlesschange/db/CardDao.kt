package de.thkoeln.fherborn.fearlesschange.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by florianherborn on 30.07.18.
 */
@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCards(card: List<Card>)

    @Query("SELECT * FROM card")
    fun getCards(): Flowable<List<Card>>
}