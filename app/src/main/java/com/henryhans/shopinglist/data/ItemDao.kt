package com.henryhans.shopinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.henryhans.shopinglist.model.Item

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Query("select* from item_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Item>>

    @Delete
    fun deleteItem(item: Item)

    @Query("delete from item_table")
    fun deleteAll()
}