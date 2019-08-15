package ca.cuvillon.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T)
}
