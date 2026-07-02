# ToDoApp-Compose

A simple Android to-do list app built with Jetpack Compose. Add, edit, complete, and delete tasks, with data persisted locally using Room.

## Features

- Add tasks via a bottom sheet
- Mark tasks as done / undone
- Edit and delete tasks
- Local persistence with Room (survives app restarts)
- Reactive UI powered by Kotlin Flow + StateFlow

## Tech Stack

- **Kotlin** & **Jetpack Compose** (Material 3)
- **Room** for local storage
- **MVVM** — `ViewModel` + `Repository` + `DAO`
- **Kotlin Coroutines / Flow**

## Architecture

```
UI (Compose screens)
  └─ TodoViewModel        // exposes tasks as StateFlow, handles insert/update/delete
       └─ TodoRepository  // abstracts data access
            └─ TodoDao    // Room DAO backed by TodoDatabase
```

## Requirements

- Android Studio
- Min SDK 24, Target SDK 37

## Getting Started

```bash
git clone <repo-url>
cd ToDoAppCompose
```

Open the project in Android Studio and run the `app` configuration on an emulator or device. Or build from the command line:

```bash
./gradlew installDebug
```
