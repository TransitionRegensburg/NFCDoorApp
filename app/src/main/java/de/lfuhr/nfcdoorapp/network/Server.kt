package de.lfuhr.nfcdoorapp.network

import android.app.Activity
import de.lfuhr.nfcdoorapp.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ludwig on 26.09.17.
 */

class Server constructor(activity: Activity) {

    private val okClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + activity!!.getResources().getString(R.string.bearer_alwo))
                .method(original.method(), original.body())
                .build()

        chain.proceed(request)
    }.build()

    private val retrofit =  Retrofit.Builder()
            .baseUrl(activity!!.getResources().getString(R.string.baseurl))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okClient)
            .build()

    private val service = retrofit.create(MyApiEndpointInterface::class.java)

    companion object {

        private var server: Server? = null;

        fun getService(activity: Activity): MyApiEndpointInterface {
            if(server == null)
                server = Server(activity)
            return server!!.service
        }
    }
}
