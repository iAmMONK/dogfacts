package com.dev.monk.dogfacts.view.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.monk.dogfacts.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class About: BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ABOUT"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.about_dialog, container, false)
}