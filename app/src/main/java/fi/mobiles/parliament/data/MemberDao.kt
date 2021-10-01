package fi.mobiles.parliament.data

import androidx.lifecycle.LiveData
import androidx.room.*

// create DAO class and define methods that can access the database
@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(member: Member)

    @Update
    fun update(member: Member)

    @Query("SELECT DISTINCT party FROM member_table")
    fun getAllParties(): List<String>

    @Query("SELECT * FROM member_table WHERE party = :party")
    fun getMembersByParty(party: String): List<Member>

    @Query("SELECT * FROM member_table WHERE Id = :key")
    fun get(key: Long): Member?

    @Query("SELECT * FROM member_table ORDER BY Id ASC")
    fun getAll(): LiveData<List<Member>>

}