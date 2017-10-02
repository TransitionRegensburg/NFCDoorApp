package de.lfuhr.nfcdoorapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ludwig on 26.09.17.
 */

class Server {


    private val okClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + bearer)
                .method(original.method(), original.body())
                .build()

        chain.proceed(request)
    }.build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okClient)
            .build()

    private val service = retrofit.create(MyApiEndpointInterface::class.java)


    companion object {

        private val server = Server()

        fun getService(): MyApiEndpointInterface {
            return server.service
        }
    }
}
