package am.example.retrofit_post_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: MutableList<UserModel.UserData>? = ArrayList()
        GlobalScope.launch (Dispatchers.IO){
            val call = UsersRetrofitService.retrofit.create(UserApi::class.java).getUserList()

            call.enqueue(object: Callback<UserModel> {

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "onFailure", Toast.LENGTH_SHORT)
                }

                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    var data: List<UserModel.UserData>? = null
                    if (response.isSuccessful){
                        data = response.body()?.data
                        Log.d("size","${data?.size}")
                    }else {
                        Toast.makeText(this@MainActivity, "Response is not successful", Toast.LENGTH_SHORT)
                        Log.d("responsIs","Response is not successful")
                    }

                    GlobalScope.launch (Dispatchers.Main){
                        val listSize = if (data?.size != null) data?.size else 0
                        for (i in 0..listSize-1)
                        list?.add(UserModel.UserData(
                            data?.get(i)?.id,
                            data?.get(i)?.title,
                            data?.get(i)?.url
                        ))
                        Log.d("dataList"," ${list}")
                        val recyclerView = findViewById<RecyclerView>(R.id.recycler_row)
                        val adapter = RecyclerAdapter(list)
                        recyclerView.setAdapter(adapter)
                        recyclerView.setLayoutManager(LinearLayoutManager(parent));
                    }
                }

            })
        }
    }
}