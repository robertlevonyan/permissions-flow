# Permissions Flow
[ ![Download](https://api.bintray.com/packages/innfinity-am/maven/PermissionsFlow/images/download.svg) ](https://bintray.com/innfinity-am/maven/PermissionsFlow/_latestVersion)

A simple library to make it easy requesting permissions in Android using Kotlin Coroutines.

## Setup

#### Gradle:

Add following line of code to your module(app) level gradle file

```groovy
    implementation 'com.innfinity:PermissionsFlow:1.0.0'
```

#### Maven:

```xml
  <dependency>
    <groupId>com.innfinity</groupId>
    <artifactId>PermissionsFlow</artifactId>
    <version>1.0.0</version>
    <type>pom</type>
  </dependency>
```

## Usage

You need to implement few steps use the library.

```kotlin
  // Open a block of PermissionFlow
  permissionFlow {
    // specify permissions you want to request 
    withPermissions(Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE)

    // add an instance of the fragment or activity, from where you are requesting permissions
    withActivity(this@MainActivity)
    //or
    withFragment(this@MainFragment)

    //request for permissions
    request().collect { granted: Boolean ->
      // do something with result
    }
    //or
    requestEach().collect { permission: Permission ->
      // get result for each permission and see if it is either granted 
      // or not, or should show a rationale explanation
    }
  }
```

## That's it!

With only few simple steps you can request permissions without splitting your code or overriding functions.

## Versions

### 1.0.0

First version of library

## Contact

- **Email**: me@robertlevonyan.com
- **Website**: https://robertlevonyan.com/
- **Medium**: https://medium.com/@RobertLevonyan
- **Twitter**: https://twitter.com/@RobertLevonyan
- **Google Play**: https://play.google.com/store/apps/dev?id=5477562049350283357

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
