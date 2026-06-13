package com.app.call.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.call.Utils.ImageHolder
import com.app.call.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivPreview.setImageBitmap(
            ImageHolder.bitmap
        )

        binding.btnRetake.setOnClickListener {

            ImageHolder.bitmap = null
            finish()
        }

        binding.btnConfirm.setOnClickListener {

            /*ImageHolder.bitmap?.let {
                // upload/send
            }

            ImageHolder.bitmap = null*/

            finish()
        }
    }
}