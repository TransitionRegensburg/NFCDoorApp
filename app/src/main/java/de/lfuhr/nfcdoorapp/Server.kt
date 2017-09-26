package de.lfuhr.nfcdoorapp

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ludwig on 26.09.17.
 */

class Server {

    private val bearer = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjJhMjBmOTdmZTFlNTI0YTBhMTkwYjA4ZWYwODlkMTVjOWQ3MWYzOWFmYmE1ZjkxODdlZTk0Yjg0ODllZjY3NjU0ODFkMDU0MGZiNTQ2YjgxIn0.eyJhdWQiOiIzIiwianRpIjoiMmEyMGY5N2ZlMWU1MjRhMGExOTBiMDhlZjA4OWQxNWM5ZDcxZjM5YWZiYTVmOTE4N2VlOTRiODQ4OWVmNjc2NTQ4MWQwNTQwZmI1NDZiODEiLCJpYXQiOjE1MDU5MjA2NzYsIm5iZiI6MTUwNTkyMDY3NiwiZXhwIjoxNTM3NDU2Njc2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.fLsk1GIdC2HWCEbI-0j12oezmvt7uOHqPnN_nbnm437YsVDxNwUGpBqFgcAPLcWHTsLMGlXA7oBSaRgcxO19645eUJzI-NmKIiF8DhhJgB7qEUMJJYLpGgBG8HiGyvfw1dOK4mwegl1aPLDvHQ-_LKo_MA5DKz2fKqEQkrOoZ7-5fmN-7YKsyE8LB-EGFvzUmduhcoY4EAqgnCayMjb0-GB3wDpH5aRknkKkKj8N3okFcsIoVL-9H2rcau00SsMh1DvF3XkyXw8xagSkZhAPyEtoQ5H7Vd29nQWvKPywygP5VtfltNef16sTCCWnHLUeL5YSR-yxqm1XvTNkR0iTMBAwmL9VRc6xEUNukYQWad3MU4CaGRam5rOJM5JkayurvvktQzqLj_cyMaYEqxyHbDV7ybp2HsK-Be8XBIgO2ivfH-w7vZrVgrLpblU4eCC58y545H-PXXTZdJE0ftaovbnUhvihM_uNCF8dbJeky_-zUnvl1JafEdJ60sqYJMKDyLC7YqQp6mCs4bNG3NfWz3GRr_u8qkojLyOK-SRfa3pMBnHvOHnIpNchzsJtcqnK44bYMw1aeE8m6OzhhPb_3jZL58urTYriPWmRtPB15MfjJ5o4iBQq0eST0_5-oMbXL7hKSDkTtefbjpF73WpLBpHC5RtyoKPPZDpc3oi5e6c"

    private val okClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", "Bearer " + bearer)
                .method(original.method(), original.body())
                .build()

        chain.proceed(request)
    }.build()


    private val BASE_URL = "http://10.0.2.2:8000/api/"
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
