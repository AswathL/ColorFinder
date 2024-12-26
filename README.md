# ColorFinder App

## Overview

ColorFinder is an Android application that helps users capture, store, and sync color information. It uses Firebase Realtime Database for syncing data across devices and Room for local data storage. The app allows users to add colors, view them in a list, and sync the data with Firebase for cloud storage.

## Features

- **Capture and Store Colors**: Add colors to your local database with custom hex color codes.
- **Sync with Firebase**: Sync the stored colors with Firebase Realtime Database.
- **Offline Support**: Local color data is stored in a Room database, ensuring offline functionality.
- **Real-time Sync**: The app syncs data to Firebase, allowing real-time updates across devices.

## Screenshots

![Screenshot 1](https://github.com/AswathL/ColorFinder/blob/84cf78fcd48fe3b59349d6801a9117d85f572686/Screenshot_20241226-225747.png)
![Screenshot 2](https://github.com/AswathL/ColorFinder/blob/84cf78fcd48fe3b59349d6801a9117d85f572686/Screenshot_20241226-225758.png)

## Technologies Used

- **Kotlin**: The programming language used for development.
- **Room Database**: Local database for storing color data.
- **Firebase Realtime Database**: Cloud database for syncing and storing color data.
- **Coroutines**: For handling background tasks like data synchronization and database operations.
- **Jetpack Compose**: For building the user interface.

## Prerequisites

To run this app locally, you will need:

- Android Studio 4.0+ with Kotlin support.
- A Firebase project setup with Firebase Realtime Database enabled.

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/ColorFinder.git
cd ColorFinder
```

### 2. Add Firebase Configuration

To set up Firebase in your project:

- Go to the [Firebase Console](https://console.firebase.google.com/).
- Create a new project (if you haven't already).
- Add an Android app to your project.
- Download the `google-services.json` file and place it in the `app` directory of your project.

### 3. Update Firebase Database Rules

For testing purposes, you can set the following rules for Firebase Realtime Database:

```json
{
  "rules": {
    ".read": "true",
    ".write": "true"
  }
}

```

### 4. Run the App

- Open the project in Android Studio.
- Build and run the app on an emulator or device.
- Add colors, sync them with Firebase, and view them in the list.

## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature`).
3. Make your changes.
4. Commit your changes (`git commit -am 'Add new feature'`).
5. Push to the branch (`git push origin feature/your-feature`).
6. Create a new pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/AswathL/ColorFinder/blob/84cf78fcd48fe3b59349d6801a9117d85f572686/LICENSE) file for details.
