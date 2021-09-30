package fi.mobiles.parliament.data

import androidx.lifecycle.LiveData
import androidx.room.*

// create DAO class and define methods that can access the database
@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(member: Member)
    @Update
    fun update(member: Member)
    @Query("SELECT * FROM member_table ORDER BY Id ASC")
    fun getAll(): LiveData<List<Member>>
    @Query("SELECT * from member_table WHERE Id = :key")
    fun get(key: Long): Member?
}