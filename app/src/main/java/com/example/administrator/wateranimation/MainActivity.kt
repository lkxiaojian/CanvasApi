package com.example.administrator.wateranimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 旋转聚合 水波纹 交互 动画
//        setContentView(R.layout.activity_main)



        setContentView(CanvasApi(this))
    }
}
