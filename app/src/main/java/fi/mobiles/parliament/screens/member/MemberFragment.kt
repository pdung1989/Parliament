package fi.mobiles.parliament.screens.member

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
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
import fi.mobiles.parliament.screens.memberlist.MemberListFragmentDirections
import fi.mobiles.parliament.screens.memberlist.MemberListViewModelFactory
import java.util.*

/**
 * Fragment for Member Detail
 */
class MemberFragment : Fragment() {
    private lateinit var binding: FragmentMemberBinding
    private lateinit var memberViewModel: MemberViewModel
    private val args: MemberFragmentArgs by navArgs()
    private val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    //private lateinit var ratingBar: RatingBar

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {

        // Get args personNumber from memberListFragment
        val personNumber = args.personNumber

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_member, container, false)
        // Specify the fragment view as the lifecycle owner of the binding.
        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize ViewModel
        val viewModelFactory = MemberViewModelFactory()
        memberViewModel = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)

       // Get member
        memberViewModel.getMember(personNumber)

        // Observe if Member object has changed
        memberViewModel.memberDetail.observe(viewLifecycleOwner, Observer { newMember ->
            newMember?.let {
                // Binding livedata object in the view model in order to communicate directly with the view
                binding.member = newMember
                binding.age.text = "Age: " + (currentYear - newMember.bornYear.toInt()).toString()

                //
                Glide
                    .with(this)
                    .load("https://avoindata.eduskunta.fi/${newMember.picture}")
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.memberImage)
            }

            val ratingBar = binding.ratingBar
            val comments = binding.comments
            // Submit button
            binding.submit.setOnClickListener {
                // Get rate from rating bar
                val rate = ratingBar.rating
                // Get comments of the user by changing from textEditable to String in order to insert to database
                val comment = comments.getText().toString()
                Toast.makeText(context, "Rating is: " + rate.toString(), Toast.LENGTH_LONG).show()
                memberViewModel.insertMemberRatingAndComment(personNumber, rate, comment)
            }
            memberViewModel.getMemberRatings(personNumber)

            //Observe Rating List
            memberViewModel.memberRatings.observe(viewLifecycleOwner, Observer {
                ratings -> ratings?.let {
                  memberViewModel.getRatingAverage(ratings)
                }
            })

            // Observe rating average
            memberViewModel.ratingAverage.observe(viewLifecycleOwner, Observer { average ->
                average?.let {
                    binding.ratingAverage.text = "Rate Average: " + String.format("%.2f", average)
                }
            })

            // Click see comments button
            binding.btnCommentList.setOnClickListener {
                memberViewModel.navigateToComment.observe(viewLifecycleOwner, Observer {
                    this.findNavController().navigate(
                        MemberFragmentDirections
                            .actionMemberFragmentToCommentListFragment(personNumber)
                    )
                    memberViewModel.onCommentNavigated()
                })

            }
            // Observe Comment list
//            memberViewModel.memberComments.observe(viewLifecycleOwner, Observer {
//                Toast.makeText(context, "comment added", Toast.LENGTH_LONG).show()
//            })
        })
        return binding.root
    }
}