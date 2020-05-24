package com.example.voicediary.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.voicediary.R
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {
    // 変数を用意する
    private var mListener: MyListener? = null

    // FragmentがActivityに追加されたら呼ばれるメソッド
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // APILevel23からは引数がActivity->Contextになっているので注意する

        // contextクラスがMyListenerを実装しているかをチェックする
        if (context is MyListener) {
            // リスナーをここでセットするようにします
            mListener = context as MyListener?
        }
    }

    // FragmentがActivityから離れたら呼ばれるメソッド
    override fun onDetach() {
        super.onDetach()
        // 画面からFragmentが離れたあとに処理が呼ばれることを避けるためにNullで初期化しておく
        mListener = null
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        view.button.setOnClickListener {
            mListener?.onClickButton()
            findNavController().navigate(R.id.action_first_to_second)
        }
        view.fab.setOnClickListener {
            findNavController().navigate(R.id.action_first_to_second)
        }
        return view
    }
    interface MyListener {
        fun onClickButton()
    }

}