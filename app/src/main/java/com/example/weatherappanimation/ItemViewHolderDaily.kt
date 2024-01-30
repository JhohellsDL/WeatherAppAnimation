package com.example.weatherappanimation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherappanimation.databinding.ItemListDailyBinding

class ItemViewHolderDaily private constructor(private val binding: ItemListDailyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: Weather,
        onClickListener: (Weather) -> Unit
    ) {
        binding.textDate.text = item.locationName
        binding.textTemperature.fadeInAndMoveUpAndChanged(item.temperatureValue.toInt())
        moveTwoText(
            binding.textDate,
            binding.imageState
        )
        itemView.setOnClickListener { onClickListener(item) }

    }

    companion object {
        fun create(parent: ViewGroup): ItemViewHolderDaily {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListDailyBinding.inflate(layoutInflater, parent, false)
            return ItemViewHolderDaily(binding)
        }
    }

    private fun LottieAnimationView.fadeInAndMoveUp() {
        val fadeInAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        fadeInAnimator.duration = ANIMATION_DURATION_MILLIS

        val translateAnimator = ObjectAnimator.ofFloat(this, "translationY", 0f, -20f)
        fadeInAnimator.duration = ANIMATION_DURATION_MILLIS

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimator, translateAnimator)
        animatorSet.start()
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

    private fun moveTwoText(textView1: TextView, imageView: ImageView) {
        textView1.alpha = 0f
        imageView.alpha = 0f

        val fadeInAnimator1 = ObjectAnimator.ofFloat(textView1, "alpha", 0f, 1f)
        fadeInAnimator1.duration = ANIMATION_DURATION_MILLIS
        val fadeInAnimator2 = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        fadeInAnimator2.duration = ANIMATION_DURATION_MILLIS

        val animator1 = ObjectAnimator.ofFloat(textView1, "translationY", -10f, 0f)
        animator1.duration = ANIMATION_DURATION_MILLIS
        val animator2 = ObjectAnimator.ofFloat(imageView, "translationY", -10f, 0f)
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

    private fun CardView.fadeInAndMoveUp() {
        val fadeInAnimator = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        fadeInAnimator.duration = ANIMATION_DURATION_MILLIS

        val translateAnimator = ObjectAnimator.ofFloat(this, "translationY", -30f, 20f)
        fadeInAnimator.duration = ANIMATION_DURATION_MILLIS

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimator, translateAnimator)
        animatorSet.start()
    }

}