package com.dev.monk.dogfacts.view.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.dev.monk.dogfacts.R
import com.dev.monk.dogfacts.databinding.AboutDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iammonk.spansimple.HtmlSpanner
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

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

        viewLifecycleOwner.lifecycleScope.launch {
            val deferred = async {
                val r =
                    BufferedReader(InputStreamReader(resources.assets.open("data_security.html")))
                val total: StringBuilder = StringBuilder()

                var line: String?
                while (r.readLine().also { line = it } != null) {
                    total.append(line).append('\n')
                }

                HtmlSpanner()(total.toString())
            }
            try {
                binding.dataSecurity.text = deferred.await()
            } catch (exception: Exception) {
                // TODO
            }
        }
    }
}
