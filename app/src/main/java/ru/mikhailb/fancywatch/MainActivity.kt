package ru.mikhailb.fancywatch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fancyWatchView = FancyWatchView(this)
        fancyWatchView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        fancyWatchView.contentDescription = "content"
        setContentView(fancyWatchView)

    }
}