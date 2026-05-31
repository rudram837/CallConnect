package com.app.call.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.call.Constants
import com.app.call.R
import com.app.call.databinding.ActivityLoginBinding
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.submit.setOnClickListener {
            val login = binding.etlogin.text.toString().trim()
            if (login.isNotEmpty()) {

                val userId = binding.etlogin.text.toString().trim()
                val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig();
                ZegoUIKitPrebuiltCallService.init(
                    application,
                    Constants.APP_ID,
                    Constants.APP_SIGN,
                    userId,
                    userId,
                    callInvitationConfig
                )
                Log.d("ZEGO", "Logged in user = $userId")
                startActivity(Intent(this, MainActivity::class.java).putExtra("name", userId))
            }
        }
    }
}