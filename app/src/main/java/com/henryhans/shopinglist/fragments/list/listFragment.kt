package com.henryhans.shopinglist.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.henryhans.shopinglist.R
import com.henryhans.shopinglist.viewmodel.ItemViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    private lateinit var m_itemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recycleview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        m_itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        m_itemViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })


        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_menu) {
            deleteAllItems()
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteAllItems(){

        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("yes"){_,_->
            m_itemViewModel.deleteAllItems()
            Toast.makeText(requireContext(), "Successful removed all items", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete all items")
        builder.setMessage("Are you sure you want delete all items?")
        builder.create().show()

    }
}