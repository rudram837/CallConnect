# CallConnect

One-to-one Audio & Video Calling Android application built using Kotlin, XML, ViewBinding, and ZEGOCLOUD Call Invitation Service.

## Features

* User Login with unique user ID
* One-to-One Audio Calling
* One-to-One Video Calling
* Real-time Call Invitations
* ZEGOCLOUD Integration

## Tech Stack

* Kotlin
* XML
* ViewBinding
* Android SDK
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

## Screenshots
<img src="screenshots/screen1.png" width="300"/>

## License

This project is for learning and portfolio purposes.
