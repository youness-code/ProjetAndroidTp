package com.example.neighbors.fragment
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.neighbors.NavigationListener
import com.example.neighbors.R
import com.example.neighbors.adapters.ListNeighborHandler
import com.example.neighbors.adapters.ListNeighborsAdapter
import com.example.neighbors.data.NeighborRepository
import com.example.neighbors.models.Neighbor
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListNeighborsFragment  : Fragment() , ListNeighborHandler {
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNeighbor: FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_neighbors_fragment, container, false)
        recyclerView = view.findViewById(R.id.neighbors_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        addNeighbor = view.findViewById(R.id.addNeighbor)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val neighbors = NeighborRepository.getInstance().getNeighbours()
        val adapter = ListNeighborsAdapter(neighbors, this)
        recyclerView.adapter = adapter
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.list_neighbor)
        }

        addNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighbourFragment())
            }
        }
    }

    override fun onDeleteNeibor(neighbor: Neighbor) {
        context?.let { context ->
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirmation Message")
            builder.setMessage("Voulez-vous supprimer ce voisin?")
            builder.setCancelable(false)
            builder.setPositiveButton("Oui") { dialogInterface: DialogInterface?, _: Int ->
                NeighborRepository.getInstance().deleteNeighbour(neighbor)
                recyclerView.adapter?.notifyDataSetChanged()
                dialogInterface?.dismiss()
            }
            builder.setNegativeButton("Non")
            { dialogInterface: DialogInterface?, _: Int ->
                dialogInterface?.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}
