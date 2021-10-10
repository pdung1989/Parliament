package fi.mobiles.parliament.screens.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentTitleBinding

/**
 * Name: DUNG TRAN (2012224)
 * Date: 3.10.2021
 * The title Fragment will be displayed when starting the App
 */
class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title, container, false)

        binding.checkButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_titleFragment_to_partyListFragment)
        }
        return binding.root
    }
}