package com.example.voicediary

import android.app.Application
import android.content.Context


class MyApplication : Application() {

    var mChoice: String? = null
        get() = field
        set(value) {
            field = value
        }
    var mSpeechTitle: String? = null
        get() = field
        set(value) {
            field = value
        }
    var mSpeechMemo: String? = null
        get() = field
        set(value) {
            field = value
        }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
            private set
        private var instance : MyApplication? = null

        fun  getInstance(): MyApplication {
            if (instance == null)
                instance = MyApplication()

            return instance!!
        }
    }

}