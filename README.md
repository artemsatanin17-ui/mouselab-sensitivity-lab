# MouseLab × Sensitivity Lab (Android)

Android WebView wrapper for the MouseLab × Sensitivity Lab HTML app.

## Build locally

1. Install Android Studio and Android SDK Platform 35.
2. Open this folder in Android Studio.
3. Let Gradle sync, then choose **Build > Build APK(s)**.
4. Debug APK output: `app/build/outputs/apk/debug/app-debug.apk`.

## Build APK without a local Android setup (GitHub Actions)

The repository includes `.github/workflows/build-apk.yml`.

1. Create a new GitHub repository.
2. Upload the contents of this folder to the repository root (including `.github`).
3. Open the repository's **Actions** tab and select **Build MouseLab APK**.
4. If needed, press **Run workflow**. When the run finishes, open it and download the artifact named `MouseLab-Sensitivity-debug-apk`.
5. Extract the ZIP and install `app-debug.apk` on the phone. Android may ask you to allow installing apps from the browser/file manager you used.

This is a debug APK for personal testing, not a Play Store release. It does not modify Standoff 2 or apply sensitivity settings directly inside the game. Mouse and keyboard event support may vary by Android/WebView version.
