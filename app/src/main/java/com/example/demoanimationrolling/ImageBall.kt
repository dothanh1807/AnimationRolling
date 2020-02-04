package com.example.demoanimationrolling

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.*
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import kotlin.random.Random
import android.util.DisplayMetrics
import com.example.demoanimationrolling.Ball

class ImageBall: ImageView {

    val list: MutableList<Ball> = mutableListOf()
    var paint: Paint? = null
    var layout_width: Int = 0
    var layout_height: Int = 0
    var mSweepAngle:Int  = 0
    var radiusBallLarge: Float = 0f
    var ballLargeX: Float = 0f
    var ballLargeY: Float = 0f

    constructor(context: Context): super(context){
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        init(context, attrs)
    }

    @SuppressLint("ResourceType")
    fun init(context: Context, attrs: AttributeSet?){
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val attrsArray = intArrayOf(android.R.attr.layout_width, android.R.attr.layout_height)
        val ta = context.obtainStyledAttributes(attrs, attrsArray)
        layout_width = ta.getLayoutDimension(0, ViewGroup.LayoutParams.MATCH_PARENT) // width of view
        layout_height = ta.getLayoutDimension(1, ViewGroup.LayoutParams.MATCH_PARENT) // height of view

        initBalls()
    }

    override fun onDraw(canvas: Canvas?) {
        for(i in 0..list.size - 1){
            paint!!.color = list[i].color
            canvas!!.drawCircle(list[i].x , list[i].y , list[i].radiusBall, paint)
        }

        super.onDraw(canvas)
    }

    fun startRolling(){
        val animator: ValueAnimator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 5000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.RESTART

        animator.addUpdateListener {
            for(i in 0..(list.size - 1)){
                list[i].sweepAngel = ++list[i].sweepAngel
                if(list[i].radiusBall < 10){
                    val sweepAngel = list[i].sweepAngel
                    list[i].x = list[i].radiusPointCenter * Math.cos((Math.PI * (sweepAngel + sweepAngel*0.4)/900)).toFloat() + width/2f
                    list[i].y = list[i].radiusPointCenter * Math.sin((Math.PI * (sweepAngel + sweepAngel*0.4)/900)).toFloat() + height/2f
                }else if((list[i].radiusBall <= 20 && list[i].radiusBall > 10) || list[i].radiusBall == 51f || list[i].radiusBall == 59f){
                    val sweepAngel = list[i].sweepAngel
                    list[i].x = list[i].radiusPointCenter * Math.cos((Math.PI * (sweepAngel + sweepAngel*0.3)/900)).toFloat() + width/2f
                    list[i].y = list[i].radiusPointCenter * Math.sin((Math.PI * (sweepAngel + sweepAngel*0.3)/900)).toFloat() + height/2f
                }else if(list[i].radiusBall < 30 && list[i].radiusBall > 20) {
                    val sweepAngel = list[i].sweepAngel
                    list[i].x = list[i].radiusPointCenter * Math.cos((Math.PI * (sweepAngel + sweepAngel*0.1)/900)).toFloat() + width/2f
                    list[i].y = list[i].radiusPointCenter * Math.sin((Math.PI * (sweepAngel + sweepAngel*0.1)/900)).toFloat() + height/2f
                }else if(list[i].radiusBall < 40 && list[i].radiusBall >= 30){
                    val sweepAngel = list[i].sweepAngel
                    list[i].x = list[i].radiusPointCenter * Math.cos((Math.PI * (sweepAngel + sweepAngel*0.1)/900)).toFloat() + width/2f
                    list[i].y = list[i].radiusPointCenter * Math.sin((Math.PI * (sweepAngel + sweepAngel*0.1)/900)).toFloat() + height/2f
                }else if(list[i].radiusBall < 50 && list[i].radiusBall >= 40){
                    val sweepAngel = list[i].sweepAngel
                    list[i].x = list[i].radiusPointCenter * Math.cos((Math.PI * (sweepAngel + sweepAngel*0.08)/900)).toFloat() + width/2f
                    list[i].y = list[i].radiusPointCenter * Math.sin((Math.PI * (sweepAngel + sweepAngel*0.08)/900)).toFloat() + height/2f
                }else {
                    val sweepAngel = list[i].sweepAngel
                    list[i].x = list[i].radiusPointCenter * Math.cos((Math.PI * (sweepAngel)/900)).toFloat() + width/2f
                    list[i].y = list[i].radiusPointCenter * Math.sin((Math.PI * (sweepAngel)/900)).toFloat() + height/2f
                }
            }
            invalidate()
        }

        animator.start()
    }

    private fun initBalls(){
        ////// I ///////

        mSweepAngle = 150
        radiusBallLarge = -400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 60f, Color.BLUE, mSweepAngle))

        mSweepAngle = 140
        radiusBallLarge = -420f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/ 900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 10f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 370
        radiusBallLarge = -400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 50f, Color.BLUE, mSweepAngle))

        mSweepAngle = 300
        radiusBallLarge = -400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 48f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 270
        radiusBallLarge = -300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 13f, Color.BLUE, mSweepAngle))

        mSweepAngle = 270
        radiusBallLarge = -390f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 17f, Color.BLUE, mSweepAngle))

        mSweepAngle = 200
        radiusBallLarge = -270f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 13f, Color.BLUE, mSweepAngle))

        mSweepAngle = 320
        radiusBallLarge = -270f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 20f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 320
        radiusBallLarge = -500f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 13f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 350
        radiusBallLarge = -510f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 7f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 380
        radiusBallLarge = -500f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 25f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 400
        radiusBallLarge = -500f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 12f, Color.BLUE, mSweepAngle))

        ///// II ///////

        mSweepAngle = -400
        radiusBallLarge = 410f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 30f, Color.YELLOW, mSweepAngle))

        mSweepAngle = -350
        radiusBallLarge = 400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 50f, Color.BLUE, mSweepAngle))

        mSweepAngle = -400
        radiusBallLarge = 300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 9f, Color.YELLOW, mSweepAngle))

        mSweepAngle = -300
        radiusBallLarge = 300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 30f, Color.BLUE, mSweepAngle))

        mSweepAngle = -250
        radiusBallLarge = 470f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 15f, Color.BLUE, mSweepAngle))

        mSweepAngle = -220
        radiusBallLarge = 400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 50f, Color.YELLOW, mSweepAngle))

        mSweepAngle = -150
        radiusBallLarge = 380f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 41f, Color.BLUE, mSweepAngle))

        mSweepAngle = -140
        radiusBallLarge = 380f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 29f, Color.BLUE, mSweepAngle))

        mSweepAngle = -50
        radiusBallLarge = 380f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 59f, Color.YELLOW, mSweepAngle))

        mSweepAngle = -40
        radiusBallLarge = 440f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 7f, Color.BLUE, mSweepAngle))

        mSweepAngle = -30
        radiusBallLarge = 300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 7f, Color.BLUE, mSweepAngle))

        mSweepAngle = -5
        radiusBallLarge = 250f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 20f, Color.BLUE, mSweepAngle))

        ////// III //////

        mSweepAngle = 120
        radiusBallLarge = 420f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 25f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 150
        radiusBallLarge = 420f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 40f, Color.BLUE, mSweepAngle))

        mSweepAngle = 90
        radiusBallLarge = 490f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 8f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 240
        radiusBallLarge = 410f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 40f, Color.BLUE, mSweepAngle))

        mSweepAngle = 220
        radiusBallLarge = 480f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 15f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 320
        radiusBallLarge = 300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 17f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 350
        radiusBallLarge = 410f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 40f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 410
        radiusBallLarge = 310f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 25f, Color.BLUE, mSweepAngle))

        mSweepAngle = 390
        radiusBallLarge = 300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 13f, Color.YELLOW, mSweepAngle))

        /// IV ///

        mSweepAngle = 470
        radiusBallLarge = 400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 51f, Color.BLUE, mSweepAngle))

        mSweepAngle = 415
        radiusBallLarge = 420f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 30f, Color.BLUE, mSweepAngle))

        mSweepAngle = 550
        radiusBallLarge = 370f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 25f, Color.BLUE, mSweepAngle))

        mSweepAngle = 530
        radiusBallLarge = 410f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 35f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 560
        radiusBallLarge = 470f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 20f, Color.BLUE, mSweepAngle))

        mSweepAngle = 450
        radiusBallLarge = 470f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 8f, Color.BLUE, mSweepAngle))

        mSweepAngle = 400
        radiusBallLarge = 490f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 13f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 640
        radiusBallLarge = 470f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 12f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 610
        radiusBallLarge = 300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 14f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 640
        radiusBallLarge = 340f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 11f, Color.BLUE, mSweepAngle))

        mSweepAngle = 710
        radiusBallLarge = 420f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 25f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 690
        radiusBallLarge = 400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 30f, Color.BLUE, mSweepAngle))

        mSweepAngle = 800
        radiusBallLarge = 460f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 18f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 790
        radiusBallLarge = 440f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 20f, Color.BLUE, mSweepAngle))

        mSweepAngle = 780
        radiusBallLarge = 300f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 20f, Color.BLUE, mSweepAngle))

        mSweepAngle = 840
        radiusBallLarge = 280f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 8f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 880
        radiusBallLarge = 400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 30f, Color.YELLOW, mSweepAngle))

        mSweepAngle = 850
        radiusBallLarge = 440f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 7f, Color.BLUE, mSweepAngle))

        mSweepAngle = 930
        radiusBallLarge = 380f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 20f, Color.BLUE, mSweepAngle))

        mSweepAngle = 950
        radiusBallLarge = 400f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 30f, Color.BLUE, mSweepAngle))

        mSweepAngle = 970
        radiusBallLarge = 450f
        ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
        ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()
        list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, 8f, Color.YELLOW, mSweepAngle))
    }

    private fun initBallsRandom(){
        // random balls
        val metrics = resources.displayMetrics.density
        val min: Int = Math.min(layout_width/metrics, layout_height/metrics).toInt()

        for(i in 0..30){
            mSweepAngle = (-360..360).random() // -360->0 balls below axis X, 0->360 balls above axix X
            radiusBallLarge = (-min..min).random().toFloat() // -300-> 0 balls to Left of axis Y, 0->300 balls to Right of axis Y
            if(radiusBallLarge < 0 && radiusBallLarge > -150f){
                radiusBallLarge = radiusBallLarge - 200f
            }else if(radiusBallLarge > 0 && radiusBallLarge < 150f){
                radiusBallLarge = radiusBallLarge + 200f
            }
            ballLargeX = layout_width/2 + radiusBallLarge* Math.cos((Math.PI * (mSweepAngle)/900)).toFloat()
            ballLargeY =  layout_height/2 + radiusBallLarge* Math.sin((Math.PI * (mSweepAngle)/900)).toFloat()

            if(i%2 == 0){
                list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, (10..60).random().toFloat(), Color.BLUE, mSweepAngle))
            }else{
                list.add(Ball(ballLargeX, ballLargeY, radiusBallLarge, (10..60).random().toFloat(), Color.YELLOW, mSweepAngle))
            }
        }
    }

}

