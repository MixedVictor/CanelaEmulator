package io.github.mixedvictor.canelaemu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.materialswitch.MaterialSwitch


class MainActivity : AppCompatActivity() {
    private val manager: ManagerViewModel by viewModels()

    private lateinit var prefixStateSwitch: MaterialSwitch
    private lateinit var createDefaultPrefixButton: Button
    private lateinit var resetDefaultPrefixButton: Button
    private lateinit var startTermuxX11Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        if (getSharedPreferences("MainActivity", MODE_PRIVATE).getBoolean("isFirstRun", true)) {

            Log.d("canela.kt.Main", "Changing activity")
            startActivity(Intent(this, SetupActivity::class.java))

//            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit {
//                putBoolean("isFirstRun", false)
//                apply()
//            }
        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.setup)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefixStateSwitch = findViewById(R.id.prefix_state_switch)
        createDefaultPrefixButton = findViewById(R.id.create_default_prefix_button)
        resetDefaultPrefixButton = findViewById(R.id.reset_default_prefix_button)
        startTermuxX11Button = findViewById(R.id.start_termux_x11_button)

        prefixStateSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && !manager.prefixIsRunning) {
                manager.prefixStart()
            } else if (!isChecked && manager.prefixIsRunning) {
                manager.prefixStop()
            }
        }

        createDefaultPrefixButton.setOnClickListener {
            manager.prefixCreate()
            prefixStateSwitch.setEnabled(true)
        }

//        if (manager.prefixIsStopping || manager.prefixIsStarting) prefixStateSwitch.setEnabled(
//            false
//        )
//
//        if (!manager.prefixIsRunning && manager.prefixCanBeReset) resetDefaultPrefixButton.setEnabled(
//            true
//        )

        resetDefaultPrefixButton.setOnClickListener {
            manager.prefixReset()
        }

        startTermuxX11Button.setOnClickListener {
            manager.launchTermuxX11()
        }
    }
}