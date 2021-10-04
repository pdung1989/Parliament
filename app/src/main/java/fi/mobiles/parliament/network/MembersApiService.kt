package fi.mobiles.parliament.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fi.mobiles.parliament.data.Member
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

// val can be initialized runtime, const not.
// compiler replaces references to const types when compiling
// val are valuated at run time.
private const val BASE_URL = "https://users.metropolia.fi/~peterh/mps"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//create a Retrofit object by using retrofit builder
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//Define an Interface for possible API calls
interface MembersApiService {
    @GET("mps.json")
    suspend fun getProperties(): List<Member>
}

//initialize the Retrofit service
object MembersApi {
    val retrofitService : MembersApiService by lazy {
        retrofit.create(MembersApiService::class.java) }
}