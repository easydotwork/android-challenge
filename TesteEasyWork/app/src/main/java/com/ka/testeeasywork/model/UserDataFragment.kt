package com.ka.testeeasywork.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ka.testeeasywork.R
import com.ka.testeeasywork.util.CircleTransform
import com.ka.testeeasywork.viewmodel.AppViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_data.*

/**
 * Created by Katarina Guedes 02/03/2020
 */
class UserDataFragment : Fragment() {

    companion object {
        fun newInstance() = UserDataFragment()
    }

    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        viewModel.user.observe(viewLifecycleOwner, Observer {
            it?.let {
                text_name_data.text = it.name
                text_email_data.text = it.email
                Picasso.with(view.context)
                    .load(it.photo)
                    .transform(CircleTransform())
                    .into(photo_frame)
            }
        })

        viewModel.setUserData(arguments?.getString("json")!!)

        view.findViewById<Button>(R.id.button_back).setOnClickListener {
            it?.let {
                findNavController().navigate(R.id.action_UserDataFragment_to_HomeFragment)
            }
        }
        view.findViewById<ImageButton>(R.id.image_button_back).setOnClickListener {
            it?.let {
                findNavController().navigate(R.id.action_UserDataFragment_to_HomeFragment)
            }
        }
    }

}