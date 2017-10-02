package de.lfuhr.nfcdoorapp


/**
 * Created by ludwig on 29.09.17.
 */

interface FragmentInteractionListener {
    var title: String
    fun switchFragment(fragment: InteractingFragmentDecorator)
    fun popFragment()
}
