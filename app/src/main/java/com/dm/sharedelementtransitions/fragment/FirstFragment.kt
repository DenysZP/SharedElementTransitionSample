package com.dm.sharedelementtransitions.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dm.sharedelementtransitions.R
import com.dm.sharedelementtransitions.extension.setTransitionName
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_first.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTransition()

        Picasso.get()
            .load("https://randomuser.me/api/portraits/lego/8.jpg")
            .into(avatarImageView)

        userContainer.setOnClickListener {
            fragmentManager?.let {
                val fragment = SecondFragment()
                it.beginTransaction()
                    .setReorderingAllowed(true)
                    .addSharedElement(
                        avatarImageView,
                        ViewCompat.getTransitionName(avatarImageView)
                    )
                    .addSharedElement(userName, ViewCompat.getTransitionName(userName))
                    .addSharedElement(email, ViewCompat.getTransitionName(email))
                    .replace(
                        R.id.fragmentContainer,
                        fragment
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun setupTransition() {
        avatarImageView.setTransitionName(R.string.avatar_transition)
        userName.setTransitionName(R.string.user_name_transition)
        email.setTransitionName(R.string.email_transition)
    }
}