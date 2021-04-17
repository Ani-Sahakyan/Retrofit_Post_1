package am.example.retrofit_post_1

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST

interface UserApi{

   @POST("photos")
   fun getUserList(): Call<UserModel>

}

object UsersRetrofitService{
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}