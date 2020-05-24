package com.example.voicediary.Speech

//
//class Speech : Fragment() {
//    private lateinit var speechRecognizer: SpeechRecognizer
//    private lateinit var recognizerIntent: Intent
//    private var speechState = false
//    private var choice : String? = null
//    val app = MyApplication.getInstance()
//    val sf = SecondFragment()
//
//
//
//    fun setupSpeechRecognizer() {
//        // SpeechRecognizer のインスタンスを生成
//        var context =
//            MyApplication.appContext
//
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
//        speechRecognizer.setRecognitionListener(
//            SpeechRecgnizerListner(
//                this
//            )
//        )
//        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//        recognizerIntent.putExtra(
//            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
//        )
//        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context?.packageName)
//    }
//    fun startListening() {
//        speechState = true
//        // 5. SpeechRecognizer の __startListening(Intent)__ に先ほど生成した SpeechRecognizer 用の Intent を渡し音声認識を開始する。
//        speechRecognizer.startListening(recognizerIntent)
//    }
//
//    fun stopListening() {
//        speechState = false
//        // 6. __stopListening()__ で音声認識を止める。
//        speechRecognizer.stopListening()
//    }
//
//    // 7. onResultsから取得した値で判定し、特定の言葉を表示する
//    fun onResultsResponse(speechText: String) {
//        Log.d("SpeechText", speechText)
//        app.mSpeech=speechText
//    }
//
//
//}