package layout

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import de.lfuhr.nfcdoorapp.InteractingFragment

import de.lfuhr.nfcdoorapp.R
import de.lfuhr.nfcdoorapp.network.SafeCallback
import de.lfuhr.nfcdoorapp.network.Server
import de.lfuhr.nfcdoorapp.data_model.Door
import de.lfuhr.nfcdoorapp.data_model.DoorsWrapper
import de.lfuhr.nfcdoorapp.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Response

class DoorListFragment : InteractingFragment() {

    private var doors: List<Door>? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        activity.title = context.resources.getString(R.string.doors)

        val binding: FragmentListBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_list, container, false)

        val call = Server.getService().getDoors()
        call.enqueue(object : SafeCallback<DoorsWrapper>(context) {
            override fun onSafeResponse(call: Call<DoorsWrapper>, response: Response<DoorsWrapper>) {
                val receivedDoors = response.body().doors!!
                doors = receivedDoors
                val doornames: MutableList<String> = ArrayList()
                for (door in receivedDoors) {
                    doornames.add(door.name!!)
                }
                binding.list.adapter = ArrayAdapter<String>(
                        context,
                        R.layout.door_list_item,
                        R.id.door_list_item,
                        doornames );
            }
        })

        binding.list.setOnItemClickListener(AdapterView.OnItemClickListener {
            adapterView, view, i, l -> activity!!.switchFragment(DoorFragment(doors!![i])) });

        // http://tips.androidgig.com/android-databinding-with-fragment/
        return binding.getRoot()

        class DoorList {
        }
    }
}