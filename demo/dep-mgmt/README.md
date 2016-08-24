Gradle dependency management
=

- cars has dependency com.google.collections:google-collections:0.8
- grocery has dependency com.google.collections:google-collections:0.9
- shopping depends on car and grocery resulting in dependency conflict

Demo 1
-

```
gradle dependencies --configuration compile
```

in shopping shows conflict being resolved into version 0.9 of google-collections.

Gain more insight into the resolution of conflicts for dependencies:

```
gradle dependencyInsight --configuration compile --dependency google
```



Demo 2
-

Disable conflict resolution by adding to the build.gradle:

```
configurations.compile.resolutionStrategy {
    failOnVersionConflict()
}
```
 
Demo 3
-

Add 

```
compile ('com.google.collections:google-collections:1.0')
```

to demonstrate adding even another dependency candidate.
Change to 

```
compile ('com.google.collections:google-collections:1.0') {
    force = true
}
```

to force the version 1.0 being resolved.

Demo 4
-

Module exclusion

Add 

```
exclude group:'com.google.collections', module:'google-collections'
```

in the compile closures for car and grocery in the shopping project

Demo 5
-

Use dependency resolve rules to select version

```
configurations.compile {
    resolutionStrategy.dependencySubstitution {
        substitute module('com.google.collections:google-collections') with module('com.google.collections:google-collections:1.0')
    }
}
```

Or even select another dependency (guava replaces google-collections dependency)

```
configurations.compile {
    resolutionStrategy.dependencySubstitution {
        substitute module('com.google.collections:google-collections') with module('com.google.guava:guava:19.0')
    }
}

```


Demo 6 - Replace bad version
-

Suppose grocery use guava 14.0, which is bad, we want to replace that specific version with 14.0.1 

```
configurations.compile {
    resolutionStrategy.dependencySubstitution {
        substitute module('com.google.collections:google-collections') with module('com.google.guava:guava:19.0')
    }
}
```

