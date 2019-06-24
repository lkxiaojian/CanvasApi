package com.example.administrator.wateranimation

import android.content.Context

//图片像素点颜色值
//粒子圆心坐标x
//粒子圆心坐标y
 //粒子半径
//粒子运动水平方向速度
//粒子运动垂直方向速度
//粒子运动水平方向加速度
//粒子运动垂直方向加速度

// class Ball(var color: Int, var x: Float, var y: Float, var r: Float, val vX: Float, val vY: Float, val aX: Float, val aY: Float) {
//    init {
//
//    }
// }


//class Ball {
//    constructor()
//    public constructor( color: Int,  x: Float,  y: Float,  r: Float,  vX: Float,  vY: Float,  aX: Float,  aY: Float){
//    }
//}


class Ball {

    var color: Int = 0 //图片像素点颜色值
    var x: Float = 0.toFloat() //粒子圆心坐标x
    var y: Float = 0.toFloat() //粒子圆心坐标y
    var r: Float = 0.toFloat() //粒子半径

    var vX: Float = 0.toFloat()//粒子运动水平方向速度
    var vY: Float = 0.toFloat()//粒子运动垂直方向速度
    var aX: Float = 0.toFloat()//粒子运动水平方向加速度
    var aY: Float = 0.toFloat()//粒子运动垂直方向加速度

}

