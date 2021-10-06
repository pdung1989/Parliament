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
    @Query("SELECT * FROM member_table WHERE personNumber = :id")
    fun getMember(id: Int): LiveData<Member>

    @Query("SELECT * FROM member_table ORDER BY first ASC")
    fun getAll(): LiveData<List<Member>>
}