package com.henryhans.shopinglist.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.henryhans.shopinglist.R
import com.henryhans.shopinglist.model.Item
import com.henryhans.shopinglist.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class addFragment : Fragment() {

    private lateinit var m_ItemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        m_ItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.btAdd.setOnClickListener{
            insertDataToDatabase()
        }
        // Inflate the layout for this fragment
        return view
    }

    private fun insertDataToDatabase(){
        val itemName      = editItemName.text.toString()
        val itemQuantity    = editItemQuantity.text.toString().toInt()
        val itemUnit      = editItemUnit.text.toString()

        if(inputCheck(itemName,itemQuantity, itemUnit)){
            val item = Item(0,itemName, itemQuantity, itemUnit)
            m_ItemViewModel.addItem(item)

            Toast.makeText(requireContext(), "Successfuly added", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(itemName: String, itemQuantity: Int, itemUnit: String):Boolean{
        return !(TextUtils.isEmpty(itemName) && (itemQuantity > 0) && TextUtils.isEmpty(itemUnit) )

    }
}