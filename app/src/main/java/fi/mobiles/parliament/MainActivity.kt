package fi.mobiles.parliament

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import fi.mobiles.parliament.data.Member
import fi.mobiles.parliament.data.MemberDatabase
import fi.mobiles.parliament.databinding.ActivityMainBinding
import fi.mobiles.parliament.network.MembersApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

}


