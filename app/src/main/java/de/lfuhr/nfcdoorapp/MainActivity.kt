package de.lfuhr.nfcdoorapp

import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity

import de.lfuhr.nfcdoorapp.databinding.ActivityMainBinding
import layout.DoorFragment
import layout.DoorListFragment

class MainActivity : FragmentActivity(), DoorFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



}
