package com.akbarprojec.foodmarket_kotlin.ui.auth.singup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.akbarprojec.foodmarket_kotlin.R
import com.akbarprojec.foodmarket_kotlin.ui.auth.AuthActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.fragment_singin.*
import kotlinx.android.synthetic.main.fragment_singup.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class SingUpFragment : Fragment() {

    var filePath: Uri? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_singup, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDummy()
        initListener()

        continueButton.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_singup_address, null)
            (activity as AuthActivity).toolbarSingUpAddress()
        }

    }

    private fun initDummy() {
        etName.setText("Naruto bin narto")
        etPassword.setText("12345678")
        etEmail.setText("naruto.ninja@konoha.com")
    }

    private fun initListener() {
        ivProfile.setOnClickListener{
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            filePath=data?.data

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)
        }else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Task cancelled", Toast.LENGTH_SHORT).show()
        }


    }
}