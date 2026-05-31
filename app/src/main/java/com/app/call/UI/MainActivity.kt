package com.app.call.UI

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.app.call.R
import com.app.call.databinding.ActivityMainBinding
import com.zegocloud.uikit.service.defines.ZegoUIKitUser
import java.util.Collections


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getStringExtra("name")
        binding.text.text = "Hello, ${userId}"

        binding.enter.addTextChangedListener {
            val target_user_id = binding.enter.text.toString()
            Log.d("ZEGO", "Calling = $target_user_id")
            getRReadyAudioCall(target_user_id)
            getRReadyVideoCall(target_user_id)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getRReadyAudioCall(target_user_id: String) {
        binding.audioCall.setIsVideoCall(false);
        binding.audioCall.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        binding.audioCall.setInvitees(
            Collections.singletonList(
                ZegoUIKitUser(
                    target_user_id,
                    target_user_id
                )
            )
        );
    }

    private fun getRReadyVideoCall(target_user_id: String) {
        binding.videoCall.setIsVideoCall(true);
        binding.videoCall.setResourceID("zego_uikit_call") // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        binding.videoCall.setInvitees(
            Collections.singletonList(
                ZegoUIKitUser(
                    target_user_id,
                    target_user_id
                )
            )
        );
    }
}