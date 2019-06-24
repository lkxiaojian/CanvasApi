package com.example.administrator.wateranimation

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.os.Build
import android.util.Log
import android.view.View


class SaveRetoreView : View {
    var mPaint: Paint? = null

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint?.color = Color.RED
        mPaint?.strokeWidth = 4f
        mPaint?.style = Paint.Style.STROKE
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        /**
         * 1.canvas内部对于状态的保存存放在栈中
         * 2.可以多次调用save保存canvas的状态，并且可以通过getSaveCount方法获取保存的状态个数
         * 3.可以通过restore方法返回最近一次save前的状态，也可以通过restoreToCount返回指定save状态。指定save状态之后的状态全部被清除
         * 4.saveLayer可以创建新的图层，之后的绘制都会在这个图层之上绘制，直到调用restore方法
         * 注意：绘制的坐标系不能超过图层的范围， saveLayerAlpha对图层增加了透明度信息
         */

//        canvas?.drawRect(0f, 0f, 200f, 200f, mPaint)
//        var state = canvas.save()//对canvas 状态进行保存
//        var saveCount = canvas.saveCount //保存 save 的次数
//        Log.e("TAG", "saveCount--->" + saveCount)
//        canvas.translate(50f, 50f)
//        mPaint?.color = Color.GRAY
//        canvas?.drawRect(0f, 0f, 200f, 200f, mPaint)
//        canvas.restore() //对canvas 状态进行恢复（恢复到最近的保存save 的状态）
//        canvas.restoreToCount(state) // 指定
//        Log.e("TAG", "saveCount--->" + canvas.saveCount)
////        canvas.translate(-50f,-50f)
//        canvas.drawLine(0f, 0f, 500f, 500f, mPaint)

        canvas?.drawRect(0f, 0f, 200f, 200f, mPaint)
        val saveLayer = canvas.saveLayer(0f, 0f,200f, 200f, mPaint)
        val matrix = Matrix()
        matrix.setTranslate(100f, 100f)
        canvas.matrix = matrix
        canvas.drawRect(0f, 0f, 200f, 200f, mPaint) //由于平移操作，导致绘制的矩形超出了图层的大小，所以绘制不完全
        canvas.restoreToCount(saveLayer)
        mPaint?.color = Color.GRAY
        canvas?.drawRect(0f, 0f, 200f, 200f, mPaint)


    }
}