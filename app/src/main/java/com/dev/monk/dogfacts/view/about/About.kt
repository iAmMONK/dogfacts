package com.dev.monk.dogfacts.view.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.AboutDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import net.nightwhistler.htmlspanner.HtmlSpanner

class About : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ABOUT"
    }

    private lateinit var binding: AboutDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.about_dialog, container, false).also {
            binding = AboutDialogBinding.bind(it)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dataSecurity.text = HtmlSpanner().fromHtml(
            resources.assets.open("data_security.html")
        )
    }
}
