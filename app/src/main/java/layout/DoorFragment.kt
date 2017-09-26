package layout

import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import de.lfuhr.nfcdoorapp.Example
import de.lfuhr.nfcdoorapp.R
import de.lfuhr.nfcdoorapp.Server
import de.lfuhr.nfcdoorapp.databinding.FragmentDoorBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DoorFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DoorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoorFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var doorId: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            doorId = arguments.getString(doorId)
        }

        val binding = DataBindingUtil.setContentView<FragmentDoorBinding>(activity, R.layout.fragment_door)
        val call = Server.getService().getDoor("1")
        call.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {
                binding.door2 = response.body().door
            }

            override fun onFailure(call: Call<Example>, t: Throwable) {}
        })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_door, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        private val ARG_PARAM1 = "doorId"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param doorId Parameter 1.
         * @return A new instance of fragment DoorFragment.
         */
        fun newInstance(doorId: String): DoorFragment {
            val fragment = DoorFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, doorId)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor