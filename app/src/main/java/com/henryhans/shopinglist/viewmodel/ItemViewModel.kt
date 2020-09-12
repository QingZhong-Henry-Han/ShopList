package com.henryhans.shopinglist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.henryhans.shopinglist.data.ItemDatabase
import com.henryhans.shopinglist.repository.ItemRepository
import com.henryhans.shopinglist.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel(application) {

    lateinit var readAllData: LiveData<List<Item>>
    private lateinit var repository: ItemRepository

    init{
        val itemDao = ItemDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllData
    }

    fun updateItem(item: Item){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateItem(item)
        }
    }

    fun addItem(item: Item){
        viewModelScope.launch(Dispatchers.IO){
            repository.addItem(item)
        }
    }

    fun deleteItem(item: Item){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(item)
        }
    }

    fun deleteAllItems(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllItems()
        }
    }
}