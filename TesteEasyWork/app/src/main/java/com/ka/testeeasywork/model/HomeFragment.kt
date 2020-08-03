package com.ka.testeeasywork.model

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.ka.testeeasywork.R
import com.ka.testeeasywork.viewmodel.AppViewModel

/**
 * Created by Katarina Guedes 02/03/2020
 */
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val scanner = IntentIntegrator.forSupportFragment(this)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setOrientationLocked(false)
                .setBarcodeImageEnabled(true)
                .setBeepEnabled(false)
                .initiateScan()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (!result.contents.isNullOrBlank()) {
                    if (viewModel.isJSONValid(result.contents)) {
                        val bundle = Bundle()
                        bundle.putString("json", result.contents)
                        findNavController().navigate(R.id.action_HomeFragment_to_UserDataFragment, bundle)
                    } else
                        Toast.makeText(view?.context, getString(R.string.error), Toast.LENGTH_LONG).show()

                } else
                    Toast.makeText(view?.context, getString(R.string.error), Toast.LENGTH_LONG).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}