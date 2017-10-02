package layout

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import de.lfuhr.nfcdoorapp.InteractingFragment
import de.lfuhr.nfcdoorapp.R
import de.lfuhr.nfcdoorapp.data_model.Door
import de.lfuhr.nfcdoorapp.databinding.FragmentDoorBinding

class DoorFragment(val door: Door) : InteractingFragment() {
    constructor() : this(Door())

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        activity.title = context.resources.getString(R.string.door) + ": " + door.name

        val binding: FragmentDoorBinding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_door, container, false)

        binding.editDoorButton.setOnClickListener({
            activity.switchFragment(DoorEditFragment(door))  })

        binding.door = door

        return binding.getRoot() //http://tips.androidgig.com/android-databinding-with-fragment/
    }
}