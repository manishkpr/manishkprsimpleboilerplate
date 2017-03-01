# manishkprsimpleboilerplate
[![](https://jitpack.io/v/manishkpr/manishkprsimpleboilerplate.svg)](https://jitpack.io/#manishkpr/manishkprsimpleboilerplate)

## Simple Android Boilerplate

I have created the simple Android MVP Boilerplate

- Support libraries

- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) 
- [Retrofit 2](http://square.github.io/retrofit/)
- [Timber](https://github.com/JakeWharton/timber)
- [Glide](https://github.com/bumptech/glide)
- [GSON](https://github.com/google/gson)

## Requirements

- JDK 1.8
- [Android SDK](http://developer.android.com/sdk/index.html).
- Android N [(API 25) ](http://developer.android.com/tools/revisions/platforms.html).
- Latest Android SDK Tools and build tools.

You can - [Download Example ] (https://github.com/manishkpr/manishkprsimpleboilerplate/archive/master.zip) to see how it's working

or You can use with gradle or maven as below dependencies

## For Gradle 

## Step 1. 
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
## Step 2. 
Add the dependency

	dependencies {
	        compile 'com.github.manishkpr:manishkprsimpleboilerplate:v1.0.4'
	}
## For Maven

## Step 1. 
Add the JitPack repository to your build file

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
## Step 2. 
Add the dependency

	<dependency>
	    <groupId>com.github.manishkpr</groupId>
	    <artifactId>manishkprsimpleboilerplate</artifactId>
	    <version>v1.0.4</version>
	</dependency>
	
### How to implement a new screen following MVP

Imagine you have to implement a sign in screen. 

1. Create a new package under `ui` called `signin`
2. Create an new Activity called `ActivitySignIn`. You could also use a Fragment.
3. Define the view interface that your Activity is going to implement. Create a new interface called `SignInMvpView` that extends `MvpView`. Add the methods that you think will be necessary, e.g. `showSignInSuccessful()`
4. Create a `SignInPresenter` class that extends `BasePresenter<SignInMvpView>`
5. Implement the methods in `SignInPresenter` that your Activity requires to perform the necessary actions, e.g. `signIn(String email)`. Once the sign in action finishes you should call `getMvpView().showSignInSuccessful()`.
6. Create a `SignInPresenterTest`and write unit tests for `signIn(email)`. Remember to mock the  `SignInMvpView` and also the `DataManager`.
7. Make your  `ActivitySignIn` implement `SignInMvpView` and implement the required methods like `showSignInSuccessful()`
8. In your activity, inject a new instance of `SignInPresenter` and call `presenter.attachView(this)` from `onCreate` and `presenter.detachView()` from `onDestroy()`. Also, set up a click listener in your button that calls `presenter.signIn(email)`.
