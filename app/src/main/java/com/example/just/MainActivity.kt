package com.example.just

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import cn.tseeey.justtext.JustTextView
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<JustTextView>(R.id.text);
        val charStr = getString(R.string.test_string)
        val ss = SpannableString(charStr)
        val pattern = Pattern.compile("2018")
        val matcher = pattern.matcher(ss.toString())
//        while (matcher.find()) {
//            val start = matcher.start()
//            val end = matcher.end()
//            val colorSpan = BackgroundColorSpan( Color.parseColor("#aa5f0c"))
//            ss.setSpan(colorSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
        val colorSpan = BackgroundColorSpan( Color.parseColor("#aa5f0c"))
        ss.setSpan(colorSpan, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = ss
    }
}
