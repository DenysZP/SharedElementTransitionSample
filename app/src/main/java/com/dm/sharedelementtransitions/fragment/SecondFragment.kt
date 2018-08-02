package com.dm.sharedelementtransitions.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.extension.setTransitionName
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_second.*
import java.lang.Exception

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                    TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTransition()

        postponeEnterTransition()

        Picasso.get()
            .load("https://randomuser.me/api/portraits/lego/8.jpg")
            .noFade()
            .into(avatarImageView, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }
            })
    }

    private fun setupTransition() {
        avatarImageView.setTransitionName(R.string.avatar_transition)
        userName.setTransitionName(R.string.user_name_transition)
        email.setTransitionName(R.string.email_transition)
    }
}