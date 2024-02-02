# SMS Sending Android App

This Android application allows users to send SMS messages directly from the app. It provides a simple user interface for entering a phone number (10 digits) and a message (up to 160 characters), and then sends the SMS when the user clicks the "Send" button. The app uses the underlying Android APIs to send SMS messages, not the default SMS app.

## Features

- Input validation for phone number length (10 digits) and message length (up to 160 characters).
- Dynamic enabling/disabling of the "Send" button based on input validation.
- Permission handling to request SMS sending permission from the user.
- User-friendly toast messages for success and failure when sending SMS.

## Getting Started

To use and build this Android app, follow these steps:

1. Clone the repository to your local machine:

git clone https://github.com/Utkarsh140503/Messenger.git

2. Open the project in Android Studio.

3. Build and run the app on an Android emulator or a physical Android device.

4. Enter a valid 10-digit phone number and a message (up to 160 characters).

5. Click the "Send" button to send the SMS.

## Permissions

The app requires the following permissions:

- `android.permission.SEND_SMS`: To send SMS messages.
# Messenger_SMS
