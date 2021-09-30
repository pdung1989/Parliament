package fi.mobiles.parliament

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDao
import fi.mobiles.parliament.data.MemberDatabase
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

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

    @Test
    @Throws(Exception::class)
    fun insertAndGetMember() {
        val member = Member(1, "Huru", "Petri", "ps", false, "1966", "Satakunta"   )
        memberDao.insert(member)
        val firstMember = memberDao.get(1)
        assertEquals(firstMember?.last, "Huru")
    }
}
