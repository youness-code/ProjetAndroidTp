package com.example.neighbors.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.neighbors.R
import com.example.neighbors.NavigationListener

import com.example.neighbors.data.NeighborRepository
import com.example.neighbors.models.Neighbor


class AddNeighbourFragment: Fragment() {
    private lateinit var Savebtn: Button
    private lateinit var nameTextEdit: EditText
    private lateinit var avatarUrlTextEdit: EditText
    private lateinit var addressTextEdit: EditText
    private lateinit var phoneNumberTextEdit: EditText
    private lateinit var aboutMeTextEdit: EditText
    private lateinit var webSiteTextEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_neighbor, container, false)

        Savebtn = view.findViewById(R.id.Savebtn)
        nameTextEdit = view.findViewById(R.id.name)
        avatarUrlTextEdit = view.findViewById(R.id.image)
        addressTextEdit = view.findViewById(R.id.address)
        phoneNumberTextEdit = view.findViewById(R.id.phone)
        aboutMeTextEdit = view.findViewById(R.id.about)
        webSiteTextEdit = view.findViewById(R.id.website)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.add_neighbor)
        }

        Savebtn.setOnClickListener {
            val neighbor = Neighbor(
                id = NeighborRepository.getInstance().getNeighbours().size + 1,
                name = nameTextEdit.text.toString(),
                avatarUrl = avatarUrlTextEdit.text.toString(),
                address = addressTextEdit.text.toString(),
                phoneNumber = phoneNumberTextEdit.text.toString(),
                aboutMe = aboutMeTextEdit.text.toString(),
                favorite = false,
                webSite = webSiteTextEdit.text.toString()
            )

            NeighborRepository.getInstance().createNeighbour(neighbor)

            activity?.onBackPressed()
        }
    }
}
