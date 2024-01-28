package com.example.weatherappanimation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherappanimation.databinding.ActivityMainBinding

private const val ANIMATION_DURATION = 1000L
private const val ANIMATION_DURATION_TEXT = 700L

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottieAnimationView: LottieAnimationView = binding.lottieAnimationView
        val lottieAnimationView2: LottieAnimationView = binding.buttonNext

        lottieAnimationView.playAnimation()

        binding.buttonNext.setOnClickListener {
            lottieAnimationView2.playAnimation()
            binding.imageWeather.fadeInAndMoveUpAndScale()
            binding.cardView.fadeInAndMoveUp()
            moveThreeObjects(
                binding.textTitle,
                binding.textSubtitle,
                binding.viewButton
            )
        }

        lottieAnimationView2.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {

            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {

            }
        })
    }

    private fun ImageView.fadeInAndMoveUpAndScale() {
        val fadeInAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        fadeInAnimator.duration = ANIMATION_DURATION

        val scaleAnimatorX = ObjectAnimator.ofFloat(this, "scaleX", 1f, 1.3f)
        scaleAnimatorX.duration = ANIMATION_DURATION

        val scaleAnimatorY = ObjectAnimator.ofFloat(this, "scaleY", 1f, 1.3f)
        scaleAnimatorY.duration = ANIMATION_DURATION

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimator, scaleAnimatorX, scaleAnimatorY)
        animatorSet.start()
    }

    private fun CardView.fadeInAndMoveUp() {
        val fadeInAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        fadeInAnimator.duration = ANIMATION_DURATION
        val translateAnimator = ObjectAnimator.ofFloat(this, "translationY", 100f, -20f)
        translateAnimator.duration = ANIMATION_DURATION
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimator, translateAnimator)
        animatorSet.start()
    }

    private fun moveThreeObjects(textView1: TextView, textView2: TextView, view: View) {
        textView1.alpha = 0f
        textView2.alpha = 0f
        view.alpha = 0f

        val fadeInAnimator1 = ObjectAnimator.ofFloat(textView1, "alpha", 0f, 1f)
        fadeInAnimator1.duration = ANIMATION_DURATION_TEXT
        val fadeInAnimator2 = ObjectAnimator.ofFloat(textView2, "alpha", 0f, 1f)
        fadeInAnimator2.duration = ANIMATION_DURATION_TEXT
        val fadeInAnimator3 = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        fadeInAnimator3.duration = ANIMATION_DURATION_TEXT

        val animator1 = ObjectAnimator.ofFloat(textView1, "translationY", 0f, 10f)
        animator1.duration = ANIMATION_DURATION_TEXT
        val animator2 = ObjectAnimator.ofFloat(textView2, "translationY", 0f, 10f)
        animator2.duration = ANIMATION_DURATION_TEXT

        val scaleAnimatorX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 1.2f)
        scaleAnimatorX.duration = ANIMATION_DURATION_TEXT
        val scaleAnimatorY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 1.2f)
        scaleAnimatorY.duration = ANIMATION_DURATION_TEXT

        animator1.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                animator2.start()
                fadeInAnimator2.start()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })
        animator2.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                scaleAnimatorX.start()
                scaleAnimatorY.start()
                fadeInAnimator3.start()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })

        fadeInAnimator1.start()
        animator1.start()
    }

}
