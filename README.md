# CleanArchitecture

This project tries to loosely follow the VIPER architecture (See https://www.objc.io/issues/13-architecture/viper/) but trying to find a balance between flexibility and overhead.

It is primarly intended as a blue print for future projects defining a set of useful libraries and frameworks.

Here is a list of libraries/frameworks I integrated:

- Butterknife: to inject views in UI code and avoid findViewById and click listeners boilerplace
- Leak Canary: a very nice memory leak detection library to be sure to not leak activities etc
- Dagger 2: for Dependency Injection. It is a quite complex framework and still is not used at its full potential (Still work in progress)
- RxJava and RxAndroid: used on the interactor layer at the moment to schedule in a nice compact way the data store or network calls on the background thread and having the observer publishing results on the UI thread.

For testing:

- Mockito
- JUnit
- Hamcrest
- Roboeletric

Everything is still work in progress.

Still TODOs:
- Improve Dagger usage (maybe injecting the activity context)
- Add a Router (R of VIPER) and having at least an other activity
- Using Fragments and having the router handle that as well
- Integrating Espresso for UI Testing
