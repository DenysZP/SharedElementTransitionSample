package com.dm.sharedelementtransitions.fragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dm.sharedelementtransitions.R
import kotlinx.android.synthetic.main.activity_fragment_holder.*

class FragmentHolderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_holder)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(
                R.id.fragmentContainer,
                FirstFragment()
            )
            .commit()
    }
}
