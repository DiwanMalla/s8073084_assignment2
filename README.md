# BookHub Application

This is a modern Android application built with Kotlin that allows users to authenticate and browse a list of books fetched from a remote API. The app is built with a focus on clean architecture, modern UI design, and best practices.

## Features

- **User Authentication:** Secure login process with username, password, and location.
- **Book Dashboard:** Displays a list of books fetched from a remote server.
- **Detailed View:** Shows more details about a selected book with a dynamic, collapsing header.
- **Light/Dark Theme:** A theme toggle allows the user to switch between light and dark modes, with the preference saved across app launches.
- **Modern UI:**
    - Animated GIF on the login screen.
    - Collapsing Toolbar on the details screen.
    - Smooth, animated transitions between screens.
- **Clean Architecture:** Follows a standard MVVM (Model-View-ViewModel) pattern, with clear separation of concerns between UI, logic, and data layers.
- **Dependency Injection:** Uses Hilt for managing dependencies throughout the application.
- **Unit Tested:** Includes unit tests for ViewModels to ensure business logic is correct.

## Tech Stack & Dependencies

- **Kotlin:** The official language for modern Android development.
- **Coroutines:** For managing background threads and asynchronous operations.
- **Hilt:** For dependency injection.
- **Retrofit & OkHttp:** For making network requests to the remote API.
- **Moshi:** For parsing JSON data from the API.
- **Glide:** For loading and displaying images and GIFs.
- **Android Jetpack:**
    - **ViewModel:** To store and manage UI-related data.
    - **Navigation Component:** To handle all in-app navigation.
    - **DataStore:** To save the user's theme preference.
    - **ViewBinding:** To easily interact with views.
- **Material Components:** For modern and customizable UI components.
- **JUnit & MockK:** For unit testing.

## Setup & Installation

1.  **Clone the Repository:**
    ```sh
    git clone <your-repository-link>
    ```
2.  **Open in Android Studio:**
    - Open Android Studio.
    - Select `File > Open` and navigate to the cloned project directory.
3.  **Sync Gradle:**
    - Android Studio will automatically start syncing the project's Gradle files. This will download all the necessary dependencies.

## Building and Running the App

Once the project has been successfully synced, you can run the application:

1.  **Select a Run Configuration:**
    - In the toolbar, ensure that the `app` configuration is selected.
2.  **Choose a Device:**
    - Select a connected physical device or an available emulator from the device dropdown menu.
3.  **Run the App:**
    - Click the green 'Run' button (or press `Shift + F10`).

The app will build, install, and launch on the selected device.
