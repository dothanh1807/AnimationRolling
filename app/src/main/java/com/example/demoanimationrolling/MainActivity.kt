package com.example.demoanimationrolling

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import com.example.demoanimationrolling.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        btn.setOnClickListener{
            animateCircleViewPoint(image)
            image.startRolling()
        }
    }

    private fun animateCircleViewPoint(view: ImageBall) {
        val scaleX = ObjectAnimator.ofFloat(view, "scaleX", 2f, 1f)
            .setDuration(1000)
        val scaleY = ObjectAnimator.ofFloat(view, "scaleY", 2f, 1f)
            .setDuration(1000)
        val rotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 180f)
            .setDuration(1000)
        rotation.interpolator = DecelerateInterpolator()

        val rotationLoop = ObjectAnimator.ofFloat(view, "rotation",
            180f, 360f)
            .setDuration(40000)
        rotationLoop.repeatMode = ObjectAnimator.RESTART
        rotationLoop.repeatCount = ObjectAnimator.INFINITE
        val animatorSet = AnimatorSet()
        animatorSet.play(scaleX).with(scaleY).with(rotation)
        animatorSet.interpolator = LinearInterpolator()
        animatorSet.start()
    }

}
