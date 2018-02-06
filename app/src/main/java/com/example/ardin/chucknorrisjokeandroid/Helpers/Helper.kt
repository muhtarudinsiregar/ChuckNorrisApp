package com.example.ardin.chucknorrisjokeandroid.Helpers

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by ardin on 06/02/18.
 */
class Helper {
    lateinit var stream: String

    fun getHttpData(urlString: String): String {
        try {
            val url = URL(urlString)

            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

            if (urlConnection.responseCode == 200) {
                val r = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val stringBuilder = StringBuilder()
                val line: String;
                line = r.readLine()

                while (line != null) {
                    stringBuilder.append(line)
                    stream = stringBuilder.toString()
                    urlConnection.disconnect()
                }
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }

        return stream
    }


}