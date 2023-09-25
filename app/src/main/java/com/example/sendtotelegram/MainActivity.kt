package com.example.sendtotelegram

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.sendtotelegram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.sendButton.setOnClickListener {
            val userId = "+996709849721"
            val message = binding.etMsg.text.toString()

            val encodedMessage = Uri.encode(message)

            if (isPackageInstalled("org.telegram.messenger")) {
                val intent = Intent(Intent.ACTION_VIEW)
                val uri = "https://t.me/$userId?start=$encodedMessage"
                intent.data = Uri.parse(uri)
                intent.setPackage("org.telegram.messenger")
                startActivity(intent)
            } else {
                Log.e("ololo", "Telegram is not installed")
            }
        }
    }

    private fun isPackageInstalled(packageName: String): Boolean {
        try {
            packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }
}
