package com.example.circlelayout

import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log.d
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.circlelayout.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var displayMetrics:DisplayMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }


    private fun init() {
        initialBtnClicks()

        displayMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            display?.getRealMetrics(displayMetrics)
        }else{
            windowManager.defaultDisplay.getRealMetrics(displayMetrics)
        }

        val screenWidth = displayMetrics.widthPixels
        val maxRadius = (screenWidth * 0.33).toInt()

        binding.root.children.forEach {
            if (it is ImageButton) {
                setPosition(it, maxRadius)
            }
        }

        binding.btnMain.setOnClickListener {
            for (i in 0..720) {

                binding.root.postDelayed({
                    binding.root.children.forEach {
                        if (it is ImageButton)
                            setRotationAngle(it, 0.5f)
                    }
                }, (5 * i).toLong())
            }
        }
    }

    private fun initialBtnClicks(){
        binding.btnKotlin.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"Kotlin",Snackbar.LENGTH_LONG).show()
        }

        binding.btnAngular.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"Angular",Snackbar.LENGTH_LONG).show()
        }

        binding.btnCPlus.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"C++",Snackbar.LENGTH_LONG).show()
        }

        binding.btnCSharp.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"C#",Snackbar.LENGTH_LONG).show()
        }

        binding.btnJava.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"Java",Snackbar.LENGTH_LONG).show()
        }

        binding.btnNodeJS.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"Node JS",Snackbar.LENGTH_LONG).show()
        }

        binding.btnPython.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"Python",Snackbar.LENGTH_LONG).show()
        }

        binding.btnReact.setOnClickListener {
            Snackbar.make(binding.btnKotlin,"React",Snackbar.LENGTH_LONG).show()
        }
    }


    private fun setPosition(view: View, position: Int) {
        val param = view.layoutParams
        if (param is ConstraintLayout.LayoutParams)
            param.circleRadius = position
    }

    private fun setRotationAngle(view: View, angle: Float) {
        val param = view.layoutParams
        if (param is ConstraintLayout.LayoutParams) {
            param.circleAngle += angle
            view.layoutParams = param
            d("angle", param.circleAngle.toString())
        }
    }
}