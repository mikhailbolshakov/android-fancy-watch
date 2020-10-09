package ru.mikhailb.fancywatch

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.util.prefs.PreferenceChangeEvent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false)

        val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val switchPref: Boolean = sharedPref.getBoolean(KEY_PREF_EXAMPLE_SWITCH, false)
        Toast.makeText(this, switchPref.toString(), Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}