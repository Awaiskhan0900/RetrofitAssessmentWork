package com.example.assessmentwork
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    override fun onBackPressed() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Exit Or Not")
        builder.setMessage("Do you want to exit app? ")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            exitProcess(1);
        })
        builder.setNegativeButton("No",
            DialogInterface.OnClickListener { dialog, id -> })
        builder.show()
    }
    // on below line we are creating variables.
    lateinit var rv: RecyclerView
    lateinit var factAdapter: FactAdapter
    lateinit var factList: ArrayList<Facts>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // on below line we are initializing
        // our variable with their ids.
        rv = findViewById(R.id.idRVCourses)

        // on below line we are initializing our list
        factList = ArrayList()

        // on below line we are calling
        // get all courses method to get data.

// get reference to button
        val btn_click_me = findViewById(R.id.button) as Button
// set on-click listener
        btn_click_me.setOnClickListener {
            getAllFacts()
        }

    }
    private fun getAllFacts() {
        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mocki.io/v1/")
            // on below line we are calling add
            // Converter factory as Gson converter factory.
            // at last we are building our retrofit builder.
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        // on below line we are calling a method to get all the courses from API.
        val call: Call<ArrayList<Facts>?>? = retrofitAPI.getAllCourses()

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call!!.enqueue(object : Callback<ArrayList<Facts>?> {
            override fun onResponse(
                call: Call<ArrayList<Facts>?>,
                response: Response<ArrayList<Facts>?>
            ) {
                if (response.isSuccessful) {
                    factList = response.body()!!
                }

                // on below line we are initializing our adapter.
                factAdapter = FactAdapter(factList)

                // on below line we are setting adapter to recycler view.
                rv.adapter = factAdapter

            }

            override fun onFailure(call: Call<ArrayList<Facts>?>, t: Throwable) {
                // displaying an error message in toast
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}