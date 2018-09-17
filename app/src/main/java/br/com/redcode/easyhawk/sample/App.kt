package br.com.redcode.easyhawk.sample

import android.app.Application
import br.com.redcode.easyhawk.library.initializeHawk

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeHawk()
    }

}