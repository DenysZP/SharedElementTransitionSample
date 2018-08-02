package com.dm.sharedelementtransitions.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.extension.addStatusBarToSharedElements
import com.dm.sharedelementtransitions.extension.setTransitionName
import com.dm.sharedelementtransitions.utils.getSharedElements
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_first.*
import kotlinx.android.synthetic.main.layout_first.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        setSupportActionBar(toolbar)
        setupTransition()

        Picasso.get()
            .load("https://randomuser.me/api/portraits/lego/8.jpg")
            .into(avatarImageView)

        userContainer.setOnClickListener {
            val sharedElements = getSharedElements(
                avatarImageView,
                toolbar,
                userName,
                email
            ).addStatusBarToSharedElements(this)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, *sharedElements)
                .toBundle()
            startActivity(Intent(this, SecondActivity::class.java), options)
        }
    }

    private fun setupTransition() {
        avatarImageView.setTransitionName(R.string.avatar_transition)
        toolbar.setTransitionName(R.string.toolbar_transition)
        userName.setTransitionName(R.string.user_name_transition)
        email.setTransitionName(R.string.email_transition)
    }
}
