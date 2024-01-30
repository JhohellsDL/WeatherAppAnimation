package com.example.weatherappanimation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappanimation.databinding.ActivitySunnyBinding

class SunnyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySunnyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySunnyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherList = listOf(
            Weather("NOW", "Sunny", 1, "25"),
            Weather("3:00 AM", "Rainy", 3, "18"),
            Weather("4:00 AM", "Partly Cloudy", 2, "22"),
            Weather("5:00 AM", "Snowy", 4, "15"),
            Weather("6:00 AM", "Partly Cloudy", 2, "22"),
            Weather("7:00 AM", "Snowy", 4, "15"),
            Weather("8:00 AM", "Sunny", 1, "28"),
            Weather("9:00 AM", "Rainy", 3, "20"),
            Weather("10:00 AM", "Partly Cloudy", 2, "24"),
            Weather("11:00 AM", "Snowy", 4, "12")
        )

        val adapter = AdapterDaily(
            onClickListener = {
                Log.d("asd", "asd")
            }
        )
        binding.recyclerViewDaily.adapter = adapter
        binding.recyclerViewDaily.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        adapter.submitList(weatherList)

        binding.textLocation.fadeInAndMoveLeft()
        binding.textTemperature.fadeInAndMoveUpAndChanged(17)
        moveTwoText(
            binding.textTemperature,
            binding.textState
        )

        moveTwoCards(
            binding.cardView,
            binding.cardViewRecycler
        )

    }

    private fun moveTwoText(textView1: TextView, textView2: TextView) {
        textView1.alpha = 0f
        textView2.alpha = 0f

        val fadeInAnimator1 = ObjectAnimator.ofFloat(textView1, "alpha", 0f, 1f)
        fadeInAnimator1.duration = ANIMATION_DURATION_MILLIS
        val fadeInAnimator2 = ObjectAnimator.ofFloat(textView2, "alpha", 0f, 1f)
        fadeInAnimator2.duration = ANIMATION_DURATION_MILLIS

        val animator1 = ObjectAnimator.ofFloat(textView1, "translationY", 0f, 10f)
        animator1.duration = ANIMATION_DURATION_MILLIS
        val animator2 = ObjectAnimator.ofFloat(textView2, "translationY", 0f, 10f)
        animator2.duration = ANIMATION_DURATION_MILLIS

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

        fadeInAnimator1.start()
        animator1.start()
    }

    private fun moveTwoCards(cardView1: CardView, cardView2: CardView) {
        cardView1.alpha = 0f
        cardView2.alpha = 0f

        val fadeInAnimator1 = ObjectAnimator.ofFloat(cardView1, "alpha", 0f, 1f)
        fadeInAnimator1.duration = ANIMATION_DURATION_MILLIS
        val fadeInAnimator2 = ObjectAnimator.ofFloat(cardView2, "alpha", 0f, 1f)
        fadeInAnimator2.duration = ANIMATION_DURATION_MILLIS

        val animator1 = ObjectAnimator.ofFloat(cardView1, "translationX", 100f, 0f)
        animator1.duration = ANIMATION_DURATION_MILLIS
        val animator2 = ObjectAnimator.ofFloat(cardView2, "translationX", 100f, 00f)
        animator2.duration = ANIMATION_DURATION_MILLIS

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

        fadeInAnimator1.start()
        animator1.start()
    }

    private fun TextView.fadeInAndMoveUpAndChanged(value: Int) {
        alpha = 0f
        translationY = 0f

        val fadeInAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        fadeInAnimator.duration = ANIMATION_DURATION_MILLIS

        val moveUpAnimator = ObjectAnimator.ofFloat(this, "translationY", 0f, 10f)
        moveUpAnimator.duration = ANIMATION_DURATION_MILLIS

        val valueAnimator = ValueAnimator.ofInt(0, value)
        valueAnimator.duration = ANIMATION_DURATION_MILLIS
        valueAnimator.addUpdateListener {
            val animatedValue = it.animatedValue as Int
            text = String.format("%s°", animatedValue.toString())
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimator, moveUpAnimator, valueAnimator)
        animatorSet.start()
    }

    private fun TextView.fadeInAndMoveLeft() {
        alpha = 0f
        translationY = 0f

        val fadeInAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        fadeInAnimator.duration = ANIMATION_DURATION_MILLIS

        val moveUpAnimator = ObjectAnimator.ofFloat(this, "translationX", 100f, 0f)
        moveUpAnimator.duration = ANIMATION_DURATION_MILLIS

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimator, moveUpAnimator)
        animatorSet.start()
    }
}