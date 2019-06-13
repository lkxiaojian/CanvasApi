package com.example.administrator.wateranimation

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.animation.AnimatorListenerAdapter
import android.view.animation.OvershootInterpolator

/**
 * 旋转扩散动画
 */

class CustomWaterAtView : View {
    //旋转圆画笔
    var mPaint: Paint? = null
    //扩展圆画笔
    var mHolePaint: Paint? = null
    //小圆点背景色
    var intArray: IntArray? = null
    //圆心坐标点 (x,y)
    var mCenterX: Float? = null
    var mCenterY: Float? = null
    //扩散圆的半径
    var mDistance: Float? = 0f
    //背景色
    var mBackGroundColor: Int = Color.WHITE
    var mRotateRadius: Float = 90f
    var mCircleRadius: Float = 18f
    var mState: Splash? = null
    //当前大圆的旋转角度
    var mCurrentRotateAngle = 0f
    var mAnimator: ValueAnimator? = null
    var mDuration: Long = 1000

    //当前大圆的半径
    var mCurrentRotateRadius = mRotateRadius
    //扩散圆的半径
    var mCurrentHoleRadius = 0f


    constructor(mContext: Context) : super(mContext) {
        val context = mContext
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    private fun init(context: Context) {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mHolePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mHolePaint?.style = Paint.Style.FILL_AND_STROKE
        mHolePaint?.color = Color.WHITE
        intArray = context.resources.getIntArray(R.array.splash_circle_colors)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mState == null) {

            mState = RotateState()
        }
        mState!!.drawState(canvas)
    }

    /**
     * 获取圆心的坐标
     * mDistance 斜对角边/2
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCenterX = w * 1f / 2
        mCenterY = h * 1f / 2
        mDistance = (Math.hypot(w.toDouble(), h.toDouble()) / 2).toFloat()
    }


    //1 旋转

    abstract class Splash {
        abstract fun drawState(canvas: Canvas)
    }


    inner class RotateState : Splash() {
        init {
            //旋转一周
            mAnimator = ValueAnimator.ofFloat(0f, (Math.PI * 2).toFloat())
            //执行两边
            mAnimator?.repeatCount = 2
            //动画时长
            mAnimator?.duration = mDuration
            mAnimator?.interpolator = LinearInterpolator()
            mAnimator?.addUpdateListener {
                mCurrentRotateAngle = it.animatedValue as Float
                invalidate()
            }
            mAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    mState = this@CustomWaterAtView.MerginState()
                }
            })

            mAnimator?.start()
        }

        override fun drawState(canvas: Canvas) {
            //绘制背景
            drawBackground(canvas)
            //绘制6个小球
            drawCircle(canvas)
        }

    }

    private fun drawCircle(canvas: Canvas) {
        var rotateAngule = Math.PI * 2 / intArray!!.size
        for (i in intArray!!.indices) {
            var angle = i * rotateAngule + mCurrentRotateAngle
            var cx = Math.cos(angle) * mCurrentRotateRadius + mCenterX!!.toFloat()
            var cy = Math.sin(angle) * mCurrentRotateRadius + mCenterY!!.toFloat()
            mPaint?.color = intArray!!.get(i)
            canvas.drawCircle(cx.toFloat(), cy.toFloat(), mCircleRadius, mPaint)
        }

    }

    private fun drawBackground(canvas: Canvas) {
//        canvas.drawColor(this@CustomWaterAtView.mBackGroundColor)
        if (mCurrentHoleRadius > 0) {
            //绘制空心圆
            var strokeWidth = mDistance?.minus(mCurrentHoleRadius)
            var radius = (strokeWidth?.div(2))?.plus(mCurrentHoleRadius)
            mHolePaint?.strokeWidth = strokeWidth!!
            canvas.drawCircle(mCenterX!!, mCenterY!!, radius!! / 10000, mHolePaint)
        } else {
            canvas.drawColor(this@CustomWaterAtView.mBackGroundColor)
        }

    }


    //2 扩散聚合

    inner class MerginState : Splash() {
        init {
            mAnimator = ValueAnimator.ofFloat(mCircleRadius, mRotateRadius)
            //动画时长
            mAnimator?.duration = mDuration
            mAnimator?.interpolator = OvershootInterpolator(10f)
            mAnimator?.addUpdateListener {
                mCurrentRotateRadius = it.animatedValue as Float
                invalidate()
            }
            mAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    mState = this@CustomWaterAtView.ExpandState()
                }
            })

            mAnimator?.reverse()
        }

        override fun drawState(canvas: Canvas) {
            //绘制背景
            drawBackground(canvas)
            //绘制6个小球
            drawCircle(canvas)
        }

    }


    //3 水波纹
    inner class ExpandState : Splash() {
        init {
            mAnimator = ValueAnimator.ofFloat(mCircleRadius, mDistance!!)
            //动画时长
            mAnimator?.duration = mDuration
            mAnimator?.interpolator = LinearInterpolator()
            mAnimator?.addUpdateListener { animation ->
                mCurrentHoleRadius = animation.animatedValue as Float
                invalidate()
            }

            mAnimator?.start()
        }

        override fun drawState(canvas: Canvas) {
            drawBackground(canvas)
        }

    }

}