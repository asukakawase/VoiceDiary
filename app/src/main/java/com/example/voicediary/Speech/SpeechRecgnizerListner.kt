package com.example.voicediary.Speech

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import com.example.voicediary.Fragment.SecondFragment

class SpeechRecgnizerListner(private val listener: SecondFragment)
    : RecognitionListener {

    interface SpeechRecgnizerListner {
        fun onResultsResponse(speechText: String)
    }

    override fun onReadyForSpeech(p0: Bundle?) {

    }

    override fun onRmsChanged(p0: Float) {
    }

    override fun onBufferReceived(p0: ByteArray?) {
    }

    override fun onPartialResults(p0: Bundle?) {
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(p0: Int) {
    }

    // 7. onResultsから取得した値で判定し、特定の言葉を表示する
    override fun onResults(bundle: Bundle?) {
        if (bundle == null) {
            listener.onResultsResponse("")
            return
        }

        val key = SpeechRecognizer.RESULTS_RECOGNITION
        val result = bundle.getStringArrayList(key)
        // なぜかスペースが入力されてしまう時があったので、スペースがあった場合は取り除くようにした。
        val speechText = result?.get(0)?.replace("\\s".toRegex(), "")

        if (speechText.isNullOrEmpty()) {
            listener.onResultsResponse("")
        } else {
            listener.onResultsResponse(speechText)
        }
    }
}