package com.app.call.UI

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.app.call.databinding.ActivityCustomCameraBinding
import com.app.call.Utils.ImageHolder
import java.io.File

class CustomCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomCameraBinding

    private var lensFacing = CameraSelector.LENS_FACING_BACK
    private var flashEnabled = false

    private var camera: Camera? = null
    private lateinit var imageCapture: ImageCapture

    companion object {
        private const val CAMERA_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (hasCameraPermission()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        }

        setupClicks()
    }

    private fun setupClicks() {

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSwitchCamera.setOnClickListener {

            lensFacing =
                if (lensFacing == CameraSelector.LENS_FACING_BACK)
                    CameraSelector.LENS_FACING_FRONT
                else
                    CameraSelector.LENS_FACING_BACK

            startCamera()
        }

        binding.btnFlash.setOnClickListener {

            flashEnabled = !flashEnabled

            camera?.cameraControl?.enableTorch(flashEnabled)
        }

        binding.btnCapture.setOnClickListener {

            // next step
             capturePhoto()
        }
    }

    private fun startCamera() {

        val cameraProviderFuture =
            ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({

            val cameraProvider =
                cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()

            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector =
                CameraSelector.Builder()
                    .requireLensFacing(lensFacing)
                    .build()

            preview.surfaceProvider =
                binding.previewView.surfaceProvider

            cameraProvider.unbindAll()

            camera = cameraProvider.bindToLifecycle(
                this,
                cameraSelector,
                preview,
                imageCapture
            )

        }, ContextCompat.getMainExecutor(this))
    }

    private fun capturePhoto() {

        if (!::imageCapture.isInitialized) return

       /* val photoFile = File(
            cacheDir,
            "IMG_${System.currentTimeMillis()}.jpg"
        )

        val outputOptions =
            ImageCapture.OutputFileOptions.Builder(photoFile)
                .build()*/

        imageCapture.takePicture(
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageCapturedCallback() {

                override fun onCaptureSuccess(image: ImageProxy) {

                    Log.d("CAMERA_", "Captured")

                    val bitmap = imageProxyToBitmap(image)

                    Log.d(
                        "CAMERA_",
                        "bitmap width = ${bitmap?.width}"
                    )

                    ImageHolder.bitmap = bitmap

                    startActivity(
                        Intent(
                            this@CustomCameraActivity,
                            PreviewActivity::class.java
                        )
                    )

                    image.close()
                }

                override fun onError(exception: ImageCaptureException) {
                }
            }
        )
    }


    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {

        val buffer = image.planes[0].buffer

        val bytes = ByteArray(buffer.remaining())

        buffer.get(bytes)

        return BitmapFactory.decodeByteArray(
            bytes,
            0,
            bytes.size
        )
    }

    private fun hasCameraPermission(): Boolean {

        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )

        if (
            requestCode == CAMERA_REQUEST_CODE &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            finish()
        }
    }
}