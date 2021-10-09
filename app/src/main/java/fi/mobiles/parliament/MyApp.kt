package fi.mobiles.parliament

import android.app.Application
import android.content.Context

/**
 * Name: DUNG TRAN (2012224)
 * Date: 1.10.2021
 */

class MyApp: Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}