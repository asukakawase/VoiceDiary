package com.example.voicediary

import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.voicediary.Dialog.CustomDialogFragment
import com.example.voicediary.Dialog.ItemsAlertDialogFragment
import com.example.voicediary.Fragment.SecondFragment


class MainActivity : AppCompatActivity(), ItemsAlertDialogFragment.MyListener{
    private lateinit var recordButton: ImageButton
    private var speechState = false
    val sf = SecondFragment()
    val app = MyApplication.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initialize layout parts
        recordButton = findViewById<View>(R.id.recordButton) as ImageButton
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("test")

        checkpermission();

        // 3. SpeechRecognizer のインスタンスを createSpeechRecognizer(Context) で生成し、 RecognitionListener を登録する。
        sf.setupSpeechRecognizer()

        recordButton.setOnClickListener() {
            if (speechState) {
                sf.stopListening()
            } else {
                val dialog = ItemsAlertDialogFragment()
                // 表示  getFagmentManager()は固定、sampleは識別タグ
                dialog.show(supportFragmentManager, "ItemsAlertDialogFragment")
        //        startListening()
            }
        }


    }

    fun checkpermission() {
        // permission チェック
        if (ContextCompat.checkSelfPermission(this, RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, RECORD_AUDIO)) {
                // 拒否した場合
                // ダイアログクラスをインスタンス化
                val dialog =
                    CustomDialogFragment()
                // 表示  getFagmentManager()は固定、sampleは識別タグ
                dialog.show(supportFragmentManager, "CustomDialogFragment")
            } else {
                // 許可した場合
                val MY_PERMISSIONS_RECORD_AUDIO = 1
                ActivityCompat.requestPermissions(this, arrayOf(RECORD_AUDIO), MY_PERMISSIONS_RECORD_AUDIO)
            }
        }
    }

    override fun onResume() {
        super.onResume();

    }
    override fun onClickButton(str:String) {
        sf.startListening()
    }

}


