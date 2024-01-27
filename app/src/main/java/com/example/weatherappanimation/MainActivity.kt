package com.example.weatherappanimation

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.weatherappanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottieAnimationView: LottieAnimationView = findViewById(R.id.lottieAnimationView)
        val lottieAnimationView2: LottieAnimationView = binding.buttonNext

        // Puedes controlar la animación según sea necesario
        // Por ejemplo, para iniciar la animación:
        lottieAnimationView.playAnimation()

        binding.buttonNext.setOnClickListener {
            lottieAnimationView2.playAnimation()
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
}
