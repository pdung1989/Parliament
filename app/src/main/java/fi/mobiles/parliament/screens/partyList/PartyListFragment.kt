package fi.mobiles.parliament.screens.partyList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.mobiles.parliament.R

class PartyListFragment : Fragment() {


    private lateinit var viewModel: PartyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_party_list, container, false)
    }

}