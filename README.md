# BookHub Application

This is a modern Android application built with Kotlin that allows users to authenticate and browse a list of books fetched from a remote API. The app is built with a focus on clean architecture, modern UI design, and best practices.

## ✨ Features

- **User Authentication:** Secure login process with username and password.
- **Book Dashboard:** Displays a list of books fetched from a remote server.
- **Detailed View:** Shows more details about a selected book with a dynamic, collapsing header.
- **Light/Dark Theme:** A theme toggle allows the user to switch between light and dark modes, with the preference saved across app launches.
- **Modern UI:**
    - Animated GIF on the login screen.
    - Collapsing Toolbar on the details screen.
    - Smooth, animated transitions between screens.
- **Clean Architecture:** Follows a standard MVVM (Model-View-ViewModel) pattern with a repository layer.
- **Dependency Injection:** Uses Hilt for managing dependencies throughout the application.
- **Unit Tested:** Includes unit tests for ViewModels to ensure business logic is correct.



## 🏗 Architecture

This app follows the principles of **Clean Architecture** with an **MVVM** (Model-View-ViewModel) pattern. This creates a separation of concerns, making the app more scalable, maintainable, and testable.

- **UI Layer (Fragments):** The UI layer is responsible for displaying the application data on the screen. It observes the ViewModel for data changes and forwards user interactions to it. It does not contain any business logic.
- **ViewModel Layer (ViewModels):** The ViewModels act as a bridge between the UI and the data layer. They fetch data from the Repository, manage the state of the UI, and expose it to the Fragments via `StateFlow`.
- **Repository Layer (Repository):** The repository is the single source of truth for the application's data. It is responsible for deciding whether to fetch data from the remote API or a local cache (if implemented).
- **Data Layer (ApiService):** This layer is responsible for all communication with the remote API using Retrofit.

## 🛠 Tech Stack & Key Components

- **Kotlin:** The official language for modern Android development.
- **Coroutines & Flow:** For managing background threads and handling asynchronous data streams.
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

## 📂 Project Structure

The project is organized into the following main packages:

- `com.example.s8073084_assignment2`
  - `data`: Contains data models (`Entity`), the repository (`MainRepository`), and the remote API service (`ApiService`).
  - `di`: Contains the Hilt dependency injection modules (`AppModule`, `NetworkModule`).
  - `ui`: Contains all the UI-related classes, organized by feature (e.g., `login`, `dashboard`, `details`).
  - `util`: Contains utility classes and extensions.

## 🚀 Setup and Build

1.  **Clone the Repository:**
    ```sh
    git clone https://github.com/DiwanMalla/s8073084_assignment2.git
    ```
2.  **Open in Android Studio:**
    - Open Android Studio (latest stable version recommended).
    - Select `File > Open` and navigate to the cloned project directory.
3.  **Sync Gradle:**
    - Android Studio will automatically start syncing the project's Gradle files. This will download all the necessary dependencies.

## 📱 Building and Running the App

Once the project has been successfully synced, you can run the application:

1.  **Select a Run Configuration:**
    - In the toolbar, ensure that the `app` configuration is selected.
2.  **Choose a Device:**
    - Select a connected physical device or an available emulator (API 31+).
3.  **Run the App:**
    - Click the green 'Run' button (or press `Shift + F10`).

The app will build, install, and launch on the selected device.

## 🧪 Running Tests

This project contains unit tests for the ViewModels. You can run them in two ways:

1.  **From Android Studio:**
    - Navigate to the test file you want to run (e.g., `LoginViewModelTest.kt`).
    - Right-click on the class name and select `Run 'LoginViewModelTest'`. 
2.  **Via Gradle:**
    - Open the terminal in Android Studio and run the following command to execute all unit tests:
    ```sh
    ./gradlew testDebugUnitTest
    ```
