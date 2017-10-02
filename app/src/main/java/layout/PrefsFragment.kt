package layout

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceFragment
import de.lfuhr.nfcdoorapp.FragmentInteractionListener

import de.lfuhr.nfcdoorapp.InteractingFragment
import de.lfuhr.nfcdoorapp.InteractingFragmentDecorator
import de.lfuhr.nfcdoorapp.R

/**
 * Created by ludwig on 02.10.17.
 */

class PrefsFragment : PreferenceFragment(), InteractingFragmentDecorator {

    lateinit var activity: FragmentInteractionListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as FragmentInteractionListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences)
    }
}