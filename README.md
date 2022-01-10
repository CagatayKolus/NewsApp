# NewsApp
An android application which uses NewsAPI for listing news.

## Prerequisites

#### 1. Check the App

If the app cannot list news, check the API key on build.gradle.kts(:app).

	buildConfigField("String", "NEWS_API_KEY", "\"860eaf0ada084fea80bc2ea8480dc0af\"")

#### 2. Ready to run.

## Features
- News Listing Feature
- Caching Results (Offline Capability)
- Pull to Refresh
- Unit Tests

## Tech Stack
- **Kotlin** - Officially supported programming language for Android development by Google
- **Kotlin DSL** - Alternative syntax to the Groovy DSL
- **Coroutines** - Perform asynchronous operations
- **Flow** - Handle the stream of data asynchronously
- **Android Architecture Components**
  - **LiveData** - Notify views about data changes
  - **Room** - Persistence library
  - **ViewModel** - UI related data holder
  - **ViewBinding** - Allows to more easily write code that interacts with views
- **Hilt** - Dependency Injection framework
- **Retrofit** - Networking library
- **Moshi** - A modern JSON library for Kotlin and Java
 
## Local Unit Tests
- The project uses MockWebServer (scriptable web server) to test API interactions.

## Screenshots
![app](https://user-images.githubusercontent.com/25778714/148808995-af7e3490-3c3a-4e4a-9154-ec598a5a1cb2.jpg)

## Architecture
![arch500](https://user-images.githubusercontent.com/25778714/113482640-3801f100-94a8-11eb-98d6-e15cb21a905b.png)
