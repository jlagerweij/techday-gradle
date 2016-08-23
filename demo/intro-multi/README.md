Multi Module Builds
-

1. settings.gradle

```
include ':helloworld'
include ':web'
```

2. gradle projects
3. build.gradle
```
subprojects {
    apply plugin 'java'
    
    version = '1.0'
    
    repositories {
        mavenCentral()
    }
    
    dependencies {
        testCompile 'junit:junit:4.12'
    }
}
```

4. api/build.gradle
```
dependencies {
    compile "commons-collections:commons-collections:3.2"
    integTest "xmlunit:xmlunit:1.3"
}

```

5. web/build.gradle
```
apply from: 'war'

dependencies {
    compile project(':helloworld')
    compile "org.springframework:spring-core:4.0.3-RELEASE"
    providedCompile "javax.servlet:javax.servlet-api:3.0.1"
}
```

6. Show partial build
cd web;  gradle build

7. Show dependent builds
cd web;  gradle buildDependent

7. Show needed builds
cd web;  gradle buildDependent buildNeeded

8. Show profiling
gradle --profile clean build
open build/reports/profile/