package com.tegar.takehometest.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.tegar.takehometest.databinding.FragmentNotificationsBinding
import com.tegar.takehometest.model.Profile
import java.io.IOException

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profile = getJson()
//        Log.d("Profile", profile?.name.toString())

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.name.text = profile?.name.toString()
        binding.numberPost.text = "Number Post \n"+ profile?.numberPost.toString()
        binding.follower.text = "Follower \n"+ profile?.follower.toString()
        binding.following.text = "Following \n"+ profile?.following.toString()
        Glide.with(this)
            .load(profile?.userAvatar.toString())
            .circleCrop()
            .into(binding.userAvatar)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getJson(): Profile? {
        val string: String?
        try {
            string = activity?.assets!!.open("profile.json").bufferedReader().use { it.readText() }

            val name = string[0].toString()
            val userAvatar = string[1].toString()
            val follower = string[2].toInt()
            val following = string[3].toInt()
            val numberPost = string[4].toInt()
            val income = string[5].toInt()

            val profile = Profile(
                name,
                userAvatar,
                follower,
                following,
                numberPost,
                income
            )

            var gson = Gson()
            var json_data = gson?.fromJson(string, profile::class.java)
            return json_data
        }catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}