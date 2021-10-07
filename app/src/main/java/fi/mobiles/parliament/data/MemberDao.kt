package fi.mobiles.parliament.data

import androidx.lifecycle.LiveData
import androidx.room.*

// create DAO class and define methods that can access the database
@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: Member)

    @Query("SELECT DISTINCT party FROM member_table")
    suspend fun getAllParties(): List<String>

    @Query("SELECT * FROM member_table WHERE party = :party")
    suspend fun getMembersByParty(party: String): List<Member>

    // Use person number as the id of each member
    @Query("SELECT * FROM member_table WHERE personNumber = :personNumber")
    fun getMember(personNumber: Int): LiveData<Member>

    @Query("SELECT * FROM member_table ORDER BY first ASC")
    fun getAll(): LiveData<List<Member>>

    //insert rating to database
    @Insert
    suspend fun insert(rating: Rating)

    //insert comment to database
    @Insert
    suspend fun insert(comment: Comment)

    @Query("SELECT * FROM rating_table WHERE personNumber = :personNumber")
    fun getMemberRatings(personNumber: Int): LiveData<List<Rating>>

    @Query("SELECT * FROM rating_table WHERE personNumber = :personNumber")
    fun getMemberComments(personNumber: Int): LiveData<List<Comment>>
}