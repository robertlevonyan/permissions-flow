# Permissions Flow
#### Versions
|PermissionsFlow|permission-compose|
|![Maven Central Version](https://img.shields.io/maven-central/v/com.robertlevonyan.components/PermissionsFlow)|![Maven Central Version](https://img.shields.io/maven-central/v/com.robertlevonyan.components/permission-compose)|


A simple library to make it easy requesting permissions in Android using Kotlin Coroutines.

## Setup

Add following line of code to your project level gradle file

```kotlin
  repositories {
    mavenCentral()
  }
```

#### Gradle:

Add following line of code to your module (app) level gradle file:

```groovy
    implementation 'com.robertlevonyan.components:PermissionsFlow:<LATEST-VERSION>'
```

#### Kotlin:

```kotlin
    implementation("com.robertlevonyan.components:PermissionsFlow:$LATEST_VERSION")
```

#### Maven:

```xml
  <dependency>
    <groupId>com.robertlevonyan.components</groupId>
    <artifactId>PermissionsFlow</artifactId>
    <version>LATEST-VERSION</version>
    <type>pom</type>
  </dependency>
```

### For Jetpack Compose
```kotlin
    implementation("com.robertlevonyan.components:permission-compose:0.0.1")
```

### Usage:

Permission flow offers 2 simple extension functions - both for for activities/fragments:

```kotlin
    requestPermissions(vararg permissionsToRequest: String)
    requestEachPermissions(vararg permissionsToRequest: String)
```

Both functions do request all permissions passed to them - the first one emits a list of `Permissions`, the second one flattens the permissions.

Here's a full code example like it would look like in an activity:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
 
    CoroutineScope(Dispatchers.Main).launch {
        // just call requestPermission and pass in all required permissions
        requestPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .collect { permissions ->
                // here you get the result of the requests, permissions holds a list of Permission requests and you can check if all of them have been granted:
                val allGranted = permissions.find { !it.isGranted } == null
                // or iterate over the permissions and check them one by one
                permissions.forEach { 
                	val granted = it.isGranted
                	// ...
                }
            }
	}
}
```

### For Jetpack Compose
```kotlin
    @Composable
    fun AppContent() {
        val permissionState = rememberPermissionState(listOf(Manifest.permission.CAMERA)) { granted ->
            // do something awesome
        }
    
        // ...

        permissionState.launchPermissionRequest()
    }
```

## That's it!

With only few simple steps you can request permissions without splitting your code or overriding functions.

## Versions
#### 1.2.8
Version and dependency bump

#### 1.2.1 - 1.2.3

Update to Java 11,
SDK 31 ready,
Minor updates

### 1.2.0

Migration to mavenCentral

#### 1.1.0

Extensions for Fragment and Activity added

### 1.0.0

First version of library

## Contact

- **Email**: me@robertlevonyan.com
- **Website**: https://robertlevonyan.com/
- **Medium**: https://medium.com/@RobertLevonyan
- **Twitter**: https://twitter.com/@RobertLevonyan
- **Google Play**: https://play.google.com/store/apps/dev?id=5477562049350283357

<a href="https://www.buymeacoffee.com/robertlevonyan">
  <img src="https://github.com/innfinity-am/permissions-flow/blob/master/Images/coffee.jpeg"  width="300" />
</a>

## Licence

```
    Permissions Flow©
    Copyright 2020 Robert Levonyan
    Url: https://github.com/innfinity-am/PermissionsFlow

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
