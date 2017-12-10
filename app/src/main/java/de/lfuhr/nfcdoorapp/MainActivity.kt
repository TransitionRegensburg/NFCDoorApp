package de.lfuhr.nfcdoorapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import de.lfuhr.nfcdoorapp.databinding.ActivityMainBinding
import layout.DoorListFragment
import layout.PrefsFragment
import android.accounts.AccountManager
import android.os.Handler


class MainActivity : AppCompatActivity(), FragmentInteractionListener {

    override var title: String
        get() = binding.title
        set(title) {binding.title = title}

    lateinit var binding: ActivityMainBinding


    override fun popFragment() { supportFragmentManager.popBackStackImmediate() }


    override fun switchFragment(fragment: InteractingFragmentDecorator) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_holder, fragment as Fragment)
                .addToBackStack(null)
                .commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val am = AccountManager.get(this)
        val options = Bundle()

        /*am.getAuthToken(
                "", // Account retrieved using getAccountsByType()
                "Manage your tasks", // Auth scope
                options, // Authenticator-specific options
                this, // Your activity
                OnTokenAcquired(), // Callback called when a token is successfully acquired
                Handler(OnError()))    // Callback called if an error occurs*/

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.myToolbar)

        val fragment = DoorListFragment()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_holder, fragment)
                .commit()
    }

    class OnError {

    }

    class OnTokenAcquired {

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.doors      -> switchFragment(DoorListFragment())
            R.id.settings   -> switchFragment(PrefsFragment())
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }



}
