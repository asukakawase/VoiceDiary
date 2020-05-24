package com.example.voicediary.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.voicediary.MyApplication
import com.example.voicediary.R


class ItemsAlertDialogFragment : DialogFragment() {
    // 変数を用意する
    private var mListener: MyListener? = null

    interface MyListener {
        fun onClickButton(str: String)
    }

    // FragmentがActivityに追加されたら呼ばれるメソッド
    override fun onAttach(context: Context) {
        if (context != null) {
            super.onAttach(context)
        }
        // APILevel23からは引数がActivity->Contextになっているので注意する

        // contextクラスがMyListenerを実装しているかをチェックする
        if (context is MyListener) {
            // リスナーをここでセットするようにします
            mListener = context
        }
    }

    // FragmentがActivityから離れたら呼ばれるメソッド
    override fun onDetach() {
        super.onDetach()
        // 画面からFragmentが離れたあとに処理が呼ばれることを避けるためにNullで初期化しておく
        mListener = null
    }

    companion object {
        // リストに表示する値を配列として定義
        val choice = arrayOf("Title", "Memo")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val app = MyApplication.getInstance()
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.itemsAlertDialogTitle)
            // .setMessage("Here Message") // setMessageは使うとリスト表示されないので注意！
            .setItems(choice) { dialog, which ->
                Toast.makeText(context, "${choice[which]} が選択されました", Toast.LENGTH_SHORT).show()
                app.mChoice = choice[which]

                if (mListener != null) {
                    mListener!!.onClickButton(choice[which])
                }
            }
            .setNegativeButton(R.string.close, null)

        return builder.create()
    }
}