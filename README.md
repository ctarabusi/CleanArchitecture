# CleanArchitecture

This project tries to loosely follow the VIPER architecture (See https://www.objc.io/issues/13-architecture/viper/) but trying to find a balance between flexibility and overhead.

It is primarly intended as a blue print for future projects defining a set of useful libraries, frameworks and best practises in the context of loosely coupled architecture composed of different layers.

Here is a list of libraries/frameworks I integrated:

- Butterknife: to inject views in UI code and avoid findViewById and click listeners boilerplace
- Leak Canary: a very nice memory leak detection library to be sure to not leak activities etc
- Dagger 2: for Dependency Injection. It is a quite complex framework and still is not used at its full potential (Still work in progress)
- RxJava and RxAndroid: used on the interactor layer at the moment to schedule in a nice compact way the data store or network calls on the background thread and having the observer publishing results on the UI thread.
- Stetho: to live inspect the UI and the network usage

For testing:

- Mockito
- JUnit
- Hamcrest
- Roboeletric

Integrated some AppCompat elements:

- Navigation view
- Snackbar
- Toolbar
- FloatingActionButton


Still TODOs:
- Improve Dagger usage (maybe injecting the activity context)
- Integrating Espresso for UI Testing
- General clean up and adding Null NotNull annotations
- TabLayout
