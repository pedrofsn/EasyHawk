package br.com.redcode.easyhawk.sample.extensions

import android.util.Log
import br.com.redcode.easyhawk.sample.BuildConfig

fun String?.toLogcat() = this?.let { if (it.isNotBlank() && BuildConfig.DEBUG) Log.i("app", it) }