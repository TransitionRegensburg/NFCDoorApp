package de.lfuhr.nfcdoorapp.network

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import de.lfuhr.nfcdoorapp.R

abstract class SafeCallback<T>(val context: Context): Callback<T> {


    final override fun onFailure(call: Call<T>, t: Throwable) {
        val msg = context.resources.getString(R.string.error_message)
        Toast.makeText(context, msg , Toast.LENGTH_LONG).show();
        Log.d(javaClass.name, Log.getStackTraceString(Exception()))
    }

    final override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.body() != null)
            onSafeResponse(call, response)
        else
            onFailure(call, NullPointerException("Response body is null"))
    }

    abstract fun onSafeResponse(call: Call<T>, response: Response<T>)

}