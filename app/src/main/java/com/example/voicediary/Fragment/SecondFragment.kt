package com.example.voicediary.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.voicediary.MyApplication
import com.example.voicediary.R
import com.example.voicediary.Speech.SpeechRecgnizerListner
import kotlinx.android.synthetic.main.fragment_second.view.*
import java.util.*


class SecondFragment : Fragment() {
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var recognizerIntent: Intent
    private var speechState = false
    private var choice : String? = null
    val app = MyApplication.getInstance()
    var title : String? =""
    var memo : String? =""
    var isSaveData : Boolean = false
    val registerTime: Date = Date()
    lateinit var titleInput: EditText
    lateinit var memoInput: EditText
  //  val handler = Handler()
    val handler = Handler(getMainLooper())
    val r: Runnable = object : Runnable {
        var count = 0
        override fun run() {
            // UIスレッド
            if (count > 60 ) { // 5回実行したら終了
                return
            }
            if(app.mChoice=="Title") {
                setTitle() // 何かやる
            }else if(app.mChoice=="Memo"){
                setMemo()
            }

            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.button.setOnClickListener {
//            val db = FirebaseFirestore.getInstance()
//            val user = DiaryData(title, memo, registerTime)
//            val snackbar = Snackbar.make(view , "Message", Snackbar.LENGTH_LONG)
//            db.collection("DiaryData")
//                .document()
//                .set(user)
//                .addOnCompleteListener { snackbar.dismiss() }
//                .addOnSuccessListener({
//                    Toast.makeText(getActivity(), "送信完了", Toast.LENGTH_LONG).show()
//                })
//                .addOnFailureListener({ Toast.makeText(getActivity(), "送信失敗", Toast.LENGTH_LONG).show()})
            findNavController().navigate(R.id.action_second_to_third)
        }
        Log.d("debug", "onViewCreated()");
    }

    override fun onResume() {
        super.onResume()
        Log.d("debug", "onResume()");
        handler.post(r)
        handler.post(r)


    }

    override fun onPause() {
        super.onPause()
        Log.d("debug", "onPause()");
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("debug", "onDestroyView()");
        handler.removeCallbacks(r);
        app.mSpeechTitle=""
        app.mSpeechMemo=""


    }
    fun setTitle(){
        titleInput= view?.findViewById<View>(R.id.titleInput) as EditText
        memoInput= view?.findViewById<View>(R.id.memoInput) as EditText
        if(app.mChoice=="Title" && app.mSpeechTitle!=null){
            titleInput.setText(app.mSpeechTitle)
            title = app.mSpeechTitle
        }
        return
    }
    fun setMemo(){
        memoInput= view?.findViewById<View>(R.id.memoInput) as EditText
        memoInput.setText(app.mSpeechMemo)
        memo=app.mSpeechMemo

    }

    fun setupSpeechRecognizer() {
        // SpeechRecognizer のインスタンスを生成
        var context =
            MyApplication.appContext

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
        speechRecognizer.setRecognitionListener(
            SpeechRecgnizerListner(this)
        )
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context?.packageName)
    }
    fun startListening() {
        speechState = true
        // 5. SpeechRecognizer の __startListening(Intent)__ に先ほど生成した SpeechRecognizer 用の Intent を渡し音声認識を開始する。
        speechRecognizer.startListening(recognizerIntent)
    }

    fun stopListening() {
        speechState = false
        // 6. __stopListening()__ で音声認識を止める。
        speechRecognizer.stopListening()
    }

    // 7. onResultsから取得した値で判定し、特定の言葉を表示する
    fun onResultsResponse(speechText: String) {
        Log.d("SpeechText", speechText)
        isSaveData = false
        if(app.mChoice=="Title") {
            app.mSpeechTitle = speechText
        }else{
            app.mSpeechMemo = speechText
        }
    }

}