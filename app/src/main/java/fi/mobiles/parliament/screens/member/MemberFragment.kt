package fi.mobiles.parliament.screens.member

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentMemberBinding
import java.util.*

/**
 * Fragment for Member Detail
 */
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding
    private lateinit var memberViewModel: MemberViewModel
    private val args: MemberFragmentArgs by navArgs()
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val personNumber = args.personNumber

        // Inflate the layout for this fragment
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member, container, false)
        // Specify the fragment view as the lifecycle owner of the binding.
        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize ViewModel
        memberViewModel = ViewModelProvider(this).get(MemberViewModel::class.java)

       // Get member
        memberViewModel.getMember(personNumber)

        // Observe if Member object has changed
        memberViewModel.memberDetail.observe(viewLifecycleOwner, Observer { newMember ->
            newMember?.let {
                // Binding livedata object in the view model in order to communicate directly with the view
                binding.member = newMember
                binding.name.text = newMember.first + " " + newMember.last
                binding.age.text = "Age: " + (currentYear - newMember.bornYear.toInt()).toString()
            }
        })
        return binding.root
    }
}