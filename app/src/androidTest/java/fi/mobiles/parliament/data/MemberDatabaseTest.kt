package fi.mobiles.parliament.data
import org.junit.Assert.*

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import fi.mobiles.parliament.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MemberDatabaseTest {

    private lateinit var memberDao: MemberDao
    private lateinit var memberDataBase: MemberDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        memberDataBase = Room.inMemoryDatabaseBuilder(context, MemberDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        memberDao = memberDataBase.memberDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        memberDataBase.close()
    }

    //Test insert member data and get the first member by Id
//    @Test
    @Throws(Exception::class)
    suspend fun insertAndGetMember() {
        val member1 = Member(1, "Huru", "Petri", "ps", false, "1966", "Satakunta"   )
        memberDao.insert(member1)
        val firstMember = memberDao.getMember(1)
        assertEquals(firstMember.bornYear, "1966" )
    }

    //Test insert member data and get list of members by party
    @Test
    suspend fun getMembersOfParty() {
        val member1 = Member(1, "Huru", "Petri", "ps", false, "1966", "Satakunta"   )
        val member2 = Member(2, "Hu", "Pet", "ps", false, "1966", "Satakunta"   )
        val member3 = Member(3, "Hur", "Petr", "vihr", false, "1966", "Uusima"   )
        memberDao.insert(member1)
        memberDao.insert(member2)
        memberDao.insert(member3)
        val membersByParty = memberDao.getMembersByParty("ps")
        assertEquals(membersByParty.size, 2)
    }

    //Test insert member data and get list of Parties
    @Test
    suspend fun getParties() {
        val member1 = Member(1, "Huru", "Petri", "ps", false, "1966", "Satakunta"   )
        val member2 = Member(2, "Hu", "Pet", "ps", false, "1966", "Satakunta"   )
        val member3 = Member(3, "Hur", "Petr", "vihr", false, "1966", "Uusima"   )
        memberDao.insert(member1)
        memberDao.insert(member2)
        memberDao.insert(member3)
        val parties = memberDao.getAllParties()
        assertTrue(parties == listOf("ps", "vihr"))
    }
}
