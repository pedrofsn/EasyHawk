package br.com.redcode.easyhawk.library

import android.app.Application
import com.orhanobut.hawk.Hawk

fun Application.initializeHawk() = Hawk.init(this).build()