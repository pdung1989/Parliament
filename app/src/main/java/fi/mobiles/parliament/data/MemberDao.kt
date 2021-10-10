package fi.mobiles.parliament.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Name: DUNG TRAN (2012224)
 * Date: 28.9.2021
 * Create DAO class and define methods that can access the database
 */
@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(member: Member)

    // Use person number as the id of each member
    @Query("SELECT * FROM member_table WHERE personNumber = :personNumber")
    fun getMember(personNumber: Int): LiveData<Member>

    @Query("SELECT * FROM member_table ORDER BY first ASC")
    fun getAll(): LiveData<List<Member>>

    //insert rating to database
    @Insert
    suspend fun insertRating(rating: Rating)

    //insert comment to database
    @Insert
    suspend fun insertComment(comment: Comment)

    @Query("SELECT rating FROM rating_table WHERE personNumber = :personNumber")
    fun getAllRatings(personNumber: Int): LiveData<List<Double>>

    @Query("SELECT * FROM comment_table WHERE personNumber = :personNumber")
    fun getAllComments(personNumber: Int): LiveData<List<Comment>>
}