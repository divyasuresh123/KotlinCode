package com.example.myapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapp.R.layout.activity_main
import com.example.myapp.ui.theme.MyAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    lateinit var textv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        getmyData()
    textv=findViewById(R.id.textView1)
    }

    private fun getmyData() {
        val retrofit1 = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/users")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofit1.getData()
        retrofitData.enqueue(object : Callback<List<DataItem>?> {
            override fun onResponse(
                call: Call<List<DataItem>?>,
                response: Response<List<DataItem>?>
            ) {
                val responseBody = response.body()!!
                val mystringBuilder = StringBuilder()
                for (myData in responseBody) {
                    mystringBuilder.append((myData.address))
                    mystringBuilder.append("\n")
                }
textv.text=mystringBuilder
            }

            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                Log.e("Main","onFAILURE" +t.message)
            }
        })
    }
}
