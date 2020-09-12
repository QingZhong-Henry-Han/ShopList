package com.henryhans.shopinglist.repository

import androidx.lifecycle.LiveData
import com.henryhans.shopinglist.data.ItemDao
import com.henryhans.shopinglist.model.Item

class ItemRepository(private val itemDao: ItemDao) {

    val readAllData: LiveData<List<Item>> = itemDao.readAllData()

    suspend fun updateItem(item: Item){
        itemDao.updateItem(item)
    }

    suspend fun addItem(item: Item){
        itemDao.addItem(item)
    }

    suspend fun deleteItem(item: Item){
        itemDao.deleteItem(item)
    }

    suspend fun deleteAllItems(){
        itemDao.deleteAll()
    }
}
