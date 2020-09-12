package com.henryhans.shopinglist.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.henryhans.shopinglist.R
import com.henryhans.shopinglist.model.Item
import com.henryhans.shopinglist.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class updateFragment : Fragment() {

    private val args by navArgs<updateFragmentArgs>()

    private lateinit var m_itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        m_itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.updateItemName.setText(args.currentUser.itemName)
        view.updateItemQuantity.setText(args.currentUser.quantity.toString())
        view.updateItemUnit.setText(args.currentUser.itemUnit)

        view.btUpdate.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem(){
        val itemName   = updateItemName.text.toString()
        val itemQuantuty = Integer.parseInt(updateItemQuantity.text.toString())
        val itemUnit   = updateItemUnit.text.toString()

        if(inputCheck(itemName, updateItemQuantity.text, itemUnit))
        {
            val item = Item(args.currentUser.id, itemName, itemQuantuty, itemUnit)
            m_itemViewModel.updateItem(item)
            Toast.makeText(requireContext(), "Item successfuly updated", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(), "Please fill all needed fields!", Toast.LENGTH_LONG).show()
        }


        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    private fun inputCheck(itemName:String, quantity:Editable, itemUnit: String): Boolean{
        return !( TextUtils.isEmpty(itemName) && quantity.isEmpty() && TextUtils.isEmpty((itemUnit)))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_menu){
            deleteCurrentItem()
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteCurrentItem(){

        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("yes"){_,_->
            m_itemViewModel.deleteItem(args.currentUser)
            Toast.makeText(requireContext(), "Successful removed ${args.currentUser.itemName}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete ${args.currentUser.itemName}?")
        builder.setMessage("Are you sure you want delete ${args.currentUser.itemName}?")
        builder.create().show()

    }
}