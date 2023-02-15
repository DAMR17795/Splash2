package www.iesmurgi.splash2

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val SPLASH_SCREEN = 3300

    lateinit var imageView: ImageView
    lateinit var imageViewPager2: ImageView

    lateinit var top: Animation
    lateinit var bottom: Animation

    lateinit var progresbar: ProgressBar
    lateinit var objeto: ObjectAnimator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Asignación variables
        imageView=findViewById<ImageView>(R.id.imageView)
        imageViewPager2=findViewById<ImageView>(R.id.imageView2)

        //Animación
        top = AnimationUtils.loadAnimation(this, R.anim.top)
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom)
        imageView.setAnimation(top)
        imageViewPager2.setAnimation(bottom)

        //Metodo mover Progress
        moverProgressBar()

        //Duración
        //objeto.setDuration(7000)

        //Listener
        objeto.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                Toast.makeText(baseContext, "Bienvenido", Toast.LENGTH_SHORT).show()
                //objeto.setVisibility(View.GONE)
            }
        })

        //Dependiendo de la versión sdk,
        //utilizará uno u otro
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_DEFAULT)
            }
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        //Abrir siguiente Ventana
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, Main2Activity::class.java))
            finish()
        }, SPLASH_SCREEN.toLong())
    }

    fun moverProgressBar() {
        progresbar = findViewById<ProgressBar>(R.id.progressBar2)
        objeto = ObjectAnimator.ofInt(progresbar, "progress", 0, 100)
    }
}