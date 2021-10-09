package fi.mobiles.parliament.screens.member

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.mobiles.parliament.data.*
import kotlinx.coroutines.launch

/**
 * ViewModel for MemberFragment
 */
class MemberViewModel: ViewModel() {
    private val database: MemberDao

    private lateinit var _memberDetail: LiveData<Member>
    val memberDetail: LiveData<Member>
        get() = _memberDetail

    private lateinit var _personNumber: LiveData<Int>
    val personNumber: LiveData<Int>
        get() = _personNumber

    private lateinit var _memberComments: LiveData<List<Comment>>
    val memberComments: LiveData<List<Comment>>
        get() = _memberComments

    private lateinit var _memberRatings: LiveData<List<Double>>
    val memberRatings: LiveData<List<Double>>
        get() = _memberRatings

    private val _ratingAverage = MutableLiveData<Double>()
    val ratingAverage: LiveData<Double>
        get() = _ratingAverage

//    // Navigate to comment fragment
//    private var _navigating = MutableLiveData<Int?>()
//    val navigating
//        get() = _navigating
//

    init {
        database = MemberDatabase.getInstance().memberDao
    }

   fun getMember(personNumber: Int) {
       _memberDetail = database.getMember(personNumber)
   }

    fun insertMemberRatingAndComment(personNumber: Int, rating: Float, comment: String) {
        viewModelScope.launch {
            database.insertRating(Rating(personNumber = personNumber,rating = rating))

            if(comment.isNotEmpty()) {
                database.insertComment(Comment(personNumber = personNumber, comment = comment))
            }
        }
    }

    fun getMemberRatings(personNumber: Int) {
        _memberRatings = database.getAllRatings(personNumber)
    }

    fun getRatingAverage(memberRatings: List<Double>) {
        if(memberRatings != null) {
            _ratingAverage.value = memberRatings.average()
        } else _ratingAverage.value = 0.0
    }

    fun getMemberComments(personNumber: Int) {
        _memberComments = database.getAllComments(personNumber)
    }

//    fun navigateToComment(personNumber: Int) {
//        _navigating.value = personNumber
//    }
//
//    fun onCommentNavigated() {
//        _navigating.value = null
//    }
//    //member Info by index
//    fun memberInfo(member: Member) {
//        party = member.party
//        imageDrawable = when(party) {
//            "sd" -> R.drawable.sd
//            "ps" -> R.drawable.ps
//            "kd" -> R.drawable.kd
//            "kesk" -> R.drawable.kesk
//            "kok" -> R.drawable.kok
//            "vihr" -> R.drawable.vihr
//            "liik" -> R.drawable.liik
//            "vas" -> R.drawable.vas
//            "r" -> R.drawable.r
//            else -> R.drawable.ic_launcher_foreground
//        }
//
//        //check if member is minister
//        status = if(member.minister) "Minister" else "Member of Parliament"
//
//        name = member.first + " " + member.last
//        age = ((Calendar.getInstance().get(Calendar.YEAR)) - member.bornYear.toInt()).toString() +
//                " " + "years-old"
//        constituency = member.constituency
//    }
}