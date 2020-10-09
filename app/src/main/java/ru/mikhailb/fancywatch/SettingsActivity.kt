package ru.mikhailb.fancywatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

const val KEY_PREF_EXAMPLE_SWITCH = "example_switch"

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, SettingsFragment())
            .commit()
    }
}