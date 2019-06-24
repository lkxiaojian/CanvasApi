package com.example.administrator.wateranimation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.view.View

/**
 * Canvas常用api
 */
class CanvasApi : View {

    var mPaint: Paint? = null

    constructor(context: Context) : super(context) {
        initData()

    }

    private fun initData() {
        mPaint = Paint()
        mPaint?.color = Color.RED
        mPaint?.strokeWidth = 4f
        mPaint?.style = Paint.Style.STROKE

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /**
         * 1平移操作
         */
//        canvas?.drawRect(0f,0f,400f,400f,mPaint)
//        canvas?.translate(50f,50f)
//        mPaint?.color=Color.GRAY
//        canvas?.drawRect(0f,0f,400f,400f,mPaint)
//        //translate 移动之后 起始点始终是 translate移动的坐标点
//        canvas?.drawLine(0f,0f,600f,600f,mPaint)

        /**
         * 2 缩放操作
         */
//        canvas?.drawRect(200f, 200f, 700f, 700f, mPaint)
//        canvas?.scale(0.5f, 0.5f)
//        canvas?.translate(200f, 200f)
//
////        canvas?.translate(200f, 200f)
////        canvas?.scale(0.5f, 0.5f)
////        canvas?.translate(-200f, -200f)
//
//        mPaint?.color = Color.GRAY
//        canvas?.drawRect(200f, 200f, 700f, 700f, mPaint)
//        canvas?.drawLine(0f, 0f, 600f, 600f, mPaint)


        //        //旋转操作
//        canvas?.translate(50f, 50f)
//        canvas?.drawRect(0f,0f, 700f,700f, mPaint)
//        canvas.rotate(45f)
//        mPaint?.color = Color.GRAY
//        canvas.drawRect(0f,0f, 700f,700f, mPaint)

//        canvas.drawRect(400f, 400f, 900f, 900f, mPaint)
//        canvas.rotate(45f, 650f, 650f) //px, py表示旋转中心的坐标
//        mPaint?.color = Color.GRAY
//        canvas.drawRect(400f, 400f, 900f, 900f, mPaint)


        //倾斜操作
//        canvas.drawRect(0f, 0f, 400f, 400f, mPaint)
////        canvas.skew(1, 0); //在X方向倾斜45度,Y轴逆时针旋转45
//        canvas.skew(0f, 2f) //在y方向倾斜45度， X轴顺时针旋转45
//        mPaint?.color = Color.GRAY
//        canvas.drawRect(0f, 0f, 400f, 400f, mPaint)


        //矩阵
        canvas.drawRect(0f, 0f, 200f, 200f, mPaint)
        var matrix = Matrix()
//        matrix.setTranslate(50f, 50f)
//        matrix.setRotate(45f)
        matrix.setScale(0.5f, 0.5f)
        canvas.matrix = matrix
        mPaint?.color = Color.GRAY
        canvas.drawRect(0f, 0f, 200f, 200f, mPaint)

    }
}