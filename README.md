# ZPHAndroidUtils

Common Android Utils

## Step 1. 

Add it in your root build.gradle at the end of repositories

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

## Step 2. 

Add the dependency


```groovy
dependencies {
        // For android compat
        implementation 'com.github.zeropercenthappy:ZPHAndroidUtils:1.3.7'
        // For androidX
        implementation 'com.github.zeropercenthappy:ZPHAndroidUtils:1.4.2'
}
```
