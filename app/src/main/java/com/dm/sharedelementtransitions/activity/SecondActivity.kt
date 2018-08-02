package com.dm.sharedelementtransitions.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.extension.setTransitionName
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.layout_second.*
import java.lang.Exception


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setSupportActionBar(toolbar)
        setupTransition()

        supportPostponeEnterTransition()

        Picasso.get()
            .load("https://randomuser.me/api/portraits/lego/8.jpg")
            .noFade()
            .into(avatarImageView, object : Callback {
                override fun onSuccess() {
                    supportStartPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    supportStartPostponedEnterTransition()
                }
            })
    }

    private fun setupTransition() {
        avatarImageView.setTransitionName(R.string.avatar_transition)
        toolbar.setTransitionName(R.string.toolbar_transition)
        userName.setTransitionName(R.string.user_name_transition)
        email.setTransitionName(R.string.email_transition)
    }
}
