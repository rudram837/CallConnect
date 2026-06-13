# CallConnect

One-to-one Audio & Video Calling Android application built using Kotlin, XML, ViewBinding, and ZEGOCLOUD Call Invitation Service. The project also includes a custom CameraX-based camera module with image preview, flash control, camera switching, and capture confirmation workflow.


## Features

* User Login with unique user ID
* One-to-One Audio Calling
* One-to-One Video Calling
* Real-time Call Invitations
* ZEGOCLOUD Integration
* Custom In-App Camera
* Camera Preview Before Sending
* Front & Back Camera Switching
* Flash Support
* Capture, Retake & Confirm Workflow

## Tech Stack

* Kotlin
* XML
* ViewBinding
* Android SDK
* CameraX
* ZEGOCLOUD Call Kit

## Setup

1. Clone the repository
2. Create a `Constants.kt` file
3. Add your ZEGOCLOUD credentials:

```kotlin
object Constants {
    const val APP_ID: Long = YOUR_APP_ID
    const val APP_SIGN = "YOUR_APP_SIGN"
    const val RESOURCE_ID = "zego_uikit_call"
}
```

4. Sync Gradle
5. Run the application

## Camera Module

The application includes a custom CameraX implementation with:

* Live Camera Preview
* Front/Back Camera Toggle
* Flash Control
* Image Capture
* Preview Screen
* Retake & Confirm Actions

## Screenshots

<img src="screenshots/screen1.png" width="300"/>

## License

This project is for learning and portfolio purposes.
