package com.dev.monk.dogfacts.utils.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

fun ViewGroup.inflateChild(@LayoutRes resId: Int): View = LayoutInflater.from(context)
    .inflate(resId, this, false)

fun View.setVisible(visible: Boolean, invisMode: Int = View.GONE) {
    visibility = if (visible) View.VISIBLE else invisMode
}

fun View.getString(@StringRes resId: Int): String = context.getString(resId)
