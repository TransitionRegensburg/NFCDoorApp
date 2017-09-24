package de.lfuhr.nfcdoorapp.connectivity

import android.content.Context

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import de.lfuhr.nfcdoorapp.beans.Door

/**
 * Created by ludwig on 23.09.17.
 */

class Server private constructor(private val baseUrl: String, private val bearer: String) {



/*
    val doors: List<Door>
    get() {
        var jsonString: String
        val callback = Response.Listener<String> { string -> run { jsonString = string } }
        doHttp("/doors", callback, errorHandler, )
        return listOf(Door())
    }
*/

    val errorHandler = Response.ErrorListener { error -> throw error }

    fun doHttp(path: String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener, context: Context) {
        val queue = Volley.newRequestQueue(context)
        val method = Request.Method.GET
        val stringRequest = BearerStringRequest(method, path,
                responseListener, errorListener, bearer)

        queue.add(stringRequest)
    }

    internal inner class BearerStringRequest(method: Int, path: String,
                                             listener: Response.Listener<String>,
                                             errorListener: Response.ErrorListener, var bearer: String)
        : StringRequest(method, baseUrl + path, listener, errorListener) {

        @Throws(AuthFailureError::class)
        override fun getHeaders(): Map<String, String> {
            val params = HashMap<String, String>()
            params.put("Authorization", "Bearer " + bearer)
            return params
        }
    }


    companion object {
        val instance: Server
            get() {
                val baseUrl = "http://10.0.2.2:8000/api"
                val bearer = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjJhMjBmOTdmZTFlNTI0YTBhMTkwYjA4ZWYwODlkMTVjOWQ3MWYzOWFmYmE1ZjkxODdlZTk0Yjg0ODllZjY3NjU0ODFkMDU0MGZiNTQ2YjgxIn0.eyJhdWQiOiIzIiwianRpIjoiMmEyMGY5N2ZlMWU1MjRhMGExOTBiMDhlZjA4OWQxNWM5ZDcxZjM5YWZiYTVmOTE4N2VlOTRiODQ4OWVmNjc2NTQ4MWQwNTQwZmI1NDZiODEiLCJpYXQiOjE1MDU5MjA2NzYsIm5iZiI6MTUwNTkyMDY3NiwiZXhwIjoxNTM3NDU2Njc2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.fLsk1GIdC2HWCEbI-0j12oezmvt7uOHqPnN_nbnm437YsVDxNwUGpBqFgcAPLcWHTsLMGlXA7oBSaRgcxO19645eUJzI-NmKIiF8DhhJgB7qEUMJJYLpGgBG8HiGyvfw1dOK4mwegl1aPLDvHQ-_LKo_MA5DKz2fKqEQkrOoZ7-5fmN-7YKsyE8LB-EGFvzUmduhcoY4EAqgnCayMjb0-GB3wDpH5aRknkKkKj8N3okFcsIoVL-9H2rcau00SsMh1DvF3XkyXw8xagSkZhAPyEtoQ5H7Vd29nQWvKPywygP5VtfltNef16sTCCWnHLUeL5YSR-yxqm1XvTNkR0iTMBAwmL9VRc6xEUNukYQWad3MU4CaGRam5rOJM5JkayurvvktQzqLj_cyMaYEqxyHbDV7ybp2HsK-Be8XBIgO2ivfH-w7vZrVgrLpblU4eCC58y545H-PXXTZdJE0ftaovbnUhvihM_uNCF8dbJeky_-zUnvl1JafEdJ60sqYJMKDyLC7YqQp6mCs4bNG3NfWz3GRr_u8qkojLyOK-SRfa3pMBnHvOHnIpNchzsJtcqnK44bYMw1aeE8m6OzhhPb_3jZL58urTYriPWmRtPB15MfjJ5o4iBQq0eST0_5-oMbXL7hKSDkTtefbjpF73WpLBpHC5RtyoKPPZDpc3oi5e6c";
                return Server(baseUrl, bearer)
            }
    }

}
