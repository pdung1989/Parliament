package fi.mobiles.parliament.screens.member

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import fi.mobiles.parliament.R
import fi.mobiles.parliament.databinding.FragmentMemberBinding
import java.util.*

/**
 * Name: DUNG TRAN (2012224)
 * Date: 2.10.2021
 * Fragment for Member Detail
 */
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding
    private lateinit var memberViewModel: MemberViewModel
    private val args: MemberFragmentArgs by navArgs()
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get args personNumber
        val personNumber = args.personNumber

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_member, container, false
        )

        // Specify the fragment view as the lifecycle owner of the binding.
        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize ViewModel
        val viewModelFactory = MemberViewModelFactory(personNumber)
        memberViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MemberViewModel::class.java)

        // Get Member with personNumber
        memberViewModel.getMember(personNumber)

        // Observe if Member object has changed
        memberViewModel.memberDetail.observe(viewLifecycleOwner, Observer { newMember ->
            newMember?.let {
                // Binding livedata object in the view model in order to communicate directly with the view
                binding.member = newMember
                binding.age.text = "Age: " + (currentYear - newMember.bornYear.toInt()).toString()

                // Use Glide to fetch pictures from internet
                Glide
                    .with(this)
                    .load("https://avoindata.eduskunta.fi/${newMember.picture}")
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.memberImage)
            }

            // Submit button
            binding.submit.setOnClickListener {
                // Get rate from rating bar
                val rate = binding.ratingBar.rating

                // Get comments of the user by changing from textEditable to String
                // in order to insert to database
                val comment = binding.comments.getText().toString()
                memberViewModel.insertMemberRatingAndComment(personNumber, rate, comment)

                // Clear the comment box after submitting
                binding.comments.text = null
            }

            // Get list of ratings of a Member by personNumber
            memberViewModel.getMemberRatings(personNumber)

            // Observe rating list
            memberViewModel.memberRatings.observe(viewLifecycleOwner, Observer { ratings ->
                ratings?.let {
                    memberViewModel.getRatingAverage(ratings)
                }
            })

            // Observe rating average
            memberViewModel.ratingAverage.observe(viewLifecycleOwner, Observer { average ->
                average?.let {
                    binding.ratingAverage.text = "Rate Average: " + String.format("%.2f", average)
                }
            })

            //Click see comments button
            binding.btnCommentList.setOnClickListener {
                this.findNavController().navigate(
                    MemberFragmentDirections
                        .actionMemberFragmentToCommentListFragment(personNumber)
                )
            }

        })
        return binding.root
    }
}