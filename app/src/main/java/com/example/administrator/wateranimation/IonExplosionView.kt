package com.example.administrator.wateranimation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator

class IonExplosionView : View {
    var bWith: Int = 0
    var bHeigh = 0
    var mBitmap: Bitmap? = null
    val d = 3f//粒子直径
    var mBalls: ArrayList<Ball> = ArrayList<Ball>()
    var mPaint: Paint? = null
    var valueAnimator: ValueAnimator? = null

    constructor(context: Context) : super(context) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic)
        bWith = mBitmap?.width!!
        bHeigh = mBitmap?.height!!

        for (i in 0 until bWith) {
            for (j in 0 until bHeigh) {
//                Log.e("tag","i--"+i+" ----j---"+j)
                var ball = Ball()
                ball.color= mBitmap?.getPixel(i, j)!!.toInt()
                ball.x = i * d + d / 2
                ball.y = j * d + d / 2
                ball.r = d / 2
                //速度(-20,20)
                ball.vX = ((Math.pow((-1).toDouble(), Math.ceil(Math.random() * 1000)) * 20 * Math.random()).toFloat())
                ball.vY = rangInt(-15, 35).toFloat()
                //加速度
                ball.aX = 0f
                ball.aY = 0.98f
                mBalls.add(ball)
            }
        }
        valueAnimator = ValueAnimator.ofFloat(0f, 1f)
        valueAnimator?.repeatCount = -1
        valueAnimator?.duration = 2000
        valueAnimator?.interpolator = LinearInterpolator()
        valueAnimator?.addUpdateListener {
            updateBall()
            invalidate()
        }
    }

    private fun updateBall() {
        for (ball in mBalls) {
            ball.x += ball.vX
            ball.y += ball.vY
            ball.vX += ball.aX
            ball.vY += ball.aY

        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(200f,200f)
        for (ball in mBalls) {
            mPaint?.color = ball.color
            canvas.drawCircle(ball.x, ball.y, ball.r, mPaint)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN){
            //执行动画
            valueAnimator?.start()
        }

        return super.onTouchEvent(event)


    }


    private fun rangInt(i: Int, j: Int): Int {
        val max = Math.max(i, j)
        val min = Math.min(i, j) - 1
        //在0到(max - min)范围内变化，取大于x的最小整数 再随机
        return (min + Math.ceil(Math.random() * (max - min))).toInt()
    }

}