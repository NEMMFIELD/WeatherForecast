package com.example.weather.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class NetConnectionDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("Please turn on internet")
            .setPositiveButton("Ok") { _, _ ->
                activity?.finish()
            }
            .create()

    companion object {
        const val TAG = "InternetConnectionDialog"
    }
}