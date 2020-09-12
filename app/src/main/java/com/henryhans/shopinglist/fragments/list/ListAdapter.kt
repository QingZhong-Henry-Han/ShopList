package com.henryhans.shopinglist.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.henryhans.shopinglist.R
import com.henryhans.shopinglist.model.Item
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    private var itemList = emptyList<Item>()

    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.itemView.itemID.text       = currentItem.id.toString()
        holder.itemView.itemName.text     = currentItem.itemName.toString()
        holder.itemView.itemQuantity.text = currentItem.quantity.toString()
        holder.itemView.itemUnit.text     = currentItem.itemUnit.toString()

        holder.itemView.rowlayout.setOnClickListener{
            val action
                    = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(items: List<Item>){
        this.itemList = items
        notifyDataSetChanged()
    }

}
