package com.example.rxandroidexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.rxandroidexample.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_combine_latest.*

class CombineLatestActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()
    private val nameBehaviorSubject = BehaviorSubject.createDefault("")
    private val ageBehaviorSubject = BehaviorSubject.createDefault("")
    private val addressBehaviorSubject = BehaviorSubject.createDefault("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combine_latest)

        et_name.doOnTextChanged { text, _, _, _ ->
            nameBehaviorSubject.onNext(text.toString())
        }

        et_age.doOnTextChanged{text, _ ,_,_ -> 3
            ageBehaviorSubject.onNext(text.toString())
        }

        et_address.doOnTextChanged { text, _, _, _ ->
            addressBehaviorSubject.onNext(text.toString())
        }

        listOf(nameBehaviorSubject,ageBehaviorSubject,addressBehaviorSubject).combineLatest {
            it.fold(true, {t1, t2 -> t1 && t2.isNotEmpty() })
        }.subscribe {
            btn_send.isEnabled = it
        }.addTo(compositeDisposable)
    }

    fun onButtonClicked(view : View){
        Toast.makeText(this,"버튼이 클릭되었습니다.",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
