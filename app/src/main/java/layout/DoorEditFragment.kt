package layout

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.lfuhr.nfcdoorapp.InteractingFragment
import de.lfuhr.nfcdoorapp.R
import de.lfuhr.nfcdoorapp.network.SafeCallback
import de.lfuhr.nfcdoorapp.network.Server
import de.lfuhr.nfcdoorapp.data_model.Door
import de.lfuhr.nfcdoorapp.data_model.DoorWrapper
import de.lfuhr.nfcdoorapp.databinding.FragmentDoorEditBinding
import retrofit2.Call
import retrofit2.Response


class DoorEditFragment(val door: Door) : InteractingFragment() {

    constructor(): this(Door())

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        activity.title = context.resources.getString(R.string.door) + ": " + door.description

        val binding: FragmentDoorEditBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_door_edit, container, false)

        binding.save.setOnClickListener({ save() })
        binding.door = door

        return binding.getRoot() //http://tips.androidgig.com/android-databinding-with-fragment/
    }

    private fun save() {
        val call = Server.getService(activity as Activity).updateDoor(door, door.id!!)
        call.enqueue(object : SafeCallback<DoorWrapper>(context) {
            override fun onSafeResponse(call: Call<DoorWrapper>, response: Response<DoorWrapper>) {
                activity!!.popFragment()
            }
        })
    }
}
