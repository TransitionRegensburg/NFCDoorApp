package de.lfuhr.nfcdoorapp


import android.content.Context
import android.support.v4.app.Fragment
import de.lfuhr.nfcdoorapp.FragmentInteractionListener

/**
 * Created by ludwig on 29.09.17.
 */

abstract class InteractingFragment: Fragment(), InteractingFragmentDecorator {

    lateinit var activity: FragmentInteractionListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as FragmentInteractionListener
    }
}
interface InteractingFragmentDecorator