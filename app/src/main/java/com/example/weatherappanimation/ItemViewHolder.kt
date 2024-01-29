package com.example.weatherappanimation

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherappanimation.databinding.ItemListBinding

class ItemViewHolder private constructor(private val binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Weather) {
        binding.textLocation.text = item.locationName
        binding.textState.text = item.stateDescription
        binding.textTemperature.text = String.format("%s°C", item.temperatureValue)

        binding.cardViewItem.fadeInAndMoveUp()
        binding.textTemperature.fadeInAndMoveUpAndChanged(item.temperatureValue.toInt())
        moveTwoText(
            binding.textLocation,
            binding.textState
        )

        when (item.state) {
            1 -> {
                binding.constraintItem.background = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.gradient_background_sunny
                )
                binding.animateSunny.fadeInAndMoveUp()
                binding.animateSunny.visibility = View.VISIBLE
                binding.animateRainy.visibility = View.GONE
                binding.animateSnowy.visibility = View.GONE
                binding.animateCloudy.visibility = View.GONE
            }

            2 -> {
                binding.constraintItem.background = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.gradient_background_cloudy
                )
                binding.animateCloudy.fadeInAndMoveUp()
                binding.animateCloudy.visibility = View.VISIBLE
                binding.animateRainy.visibility = View.GONE
                binding.animateSnowy.visibility = View.GONE
                binding.animateSunny.visibility = View.GONE
            }

            3 -> {
                binding.constraintItem.background = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.gradient_background_rainy
                )
                binding.animateRainy.fadeInAndMoveUp()
                binding.animateRainy.visibility = View.VISIBLE
                binding.animateCloudy.visibility = View.GONE
                binding.animateSnowy.visibility = View.GONE
                binding.animateSunny.visibility = View.GONE
            }

            4 -> {
                binding.constraintItem.background = ContextCompat.getDrawable(
                    binding.root.context,
                    R.drawable.gradient_background_snowy
                )
                binding.animateSnowy.visibility = View.VISIBLE
                binding.animateSnowy.fadeInAndMoveUp()
                binding.animateRainy.visibility = View.GONE
                binding.animateCloudy.visibility = View.GONE
                binding.animateSunny.visibility = View.GONE
            }
        }
    }
    companion object {
        fun create(parent: ViewGroup): ItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemListBinding.inflate(layoutInflater, parent, false)
            return ItemViewHolder(binding)
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
            text = String.format("%s°C", animatedValue.toString())
        }

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(fadeInAnimator, moveUpAnimator, valueAnimator)
        animatorSet.start()
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