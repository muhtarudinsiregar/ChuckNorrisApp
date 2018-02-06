package com.example.ardin.chucknorrisjokeandroid

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.ardin.chucknorrisjokeandroid.Helpers.Helper
import com.example.ardin.chucknorrisjokeandroid.Models.ChuckNorrisJoke
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonJoke.setOnClickListener {

            val asyncTask = @SuppressLint("StaticFieldLeak")
            object : AsyncTask<String, Void, String>() {
                val dialog = ProgressDialog(this@MainActivity)

                override fun onPreExecute() {
                    super.onPreExecute()
                    dialog.setTitle("Please waiting....")
                    dialog.show()
                }

                override fun doInBackground(vararg params: String?): String {
                    val helper = Helper()
                    println(helper.getHttpData("http://api.icndb.com/jokes/random/"))
                    Log.d("LOG", "MASUK SINI KOKKKK")
                    return helper.getHttpData("http://api.icndb.com/jokes/random/")
                }

                override fun onPostExecute(result: String) {
                    Log.d("LOG", "MASUK SINI DISMISS")
                    dialog.dismiss()

                    val chuckNorrisJoke = Gson().fromJson(result, ChuckNorrisJoke::class.java)

                    textJoke.text = chuckNorrisJoke.value.joke

                    if (textJoke.visibility == INVISIBLE) {
                        textJoke.visibility = VISIBLE
                    }
                }

            }

            runOnUiThread {
                asyncTask.execute()
            }
        }
    }
}
