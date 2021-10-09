package fi.mobiles.parliament.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fi.mobiles.parliament.data.Member
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Name: DUNG TRAN (2012224)
 * Date: 30.9.2021
 * Using Retrofit and Moshi libraries to fetch JSON data from internet
 */
private const val BASE_URL = "https://users.metropolia.fi/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Create a Retrofit object by using retrofit builder
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// Define an Interface for possible API calls
interface MembersApiService {
    @GET("~peterh/mps.json")
    suspend fun getProperties(): List<Member>
}

// Initialize the Retrofit service
object MembersApi {
    val retrofitService: MembersApiService by lazy {
        retrofit.create(MembersApiService::class.java)
    }
}