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
exclude group:'com.google.collections' group:'google-collections'
```

in the compile closures for car and grocery in the shopping project

Demo 5
-

Use dependency resolve rules to select version

```
configuration.compile.resolutionStrategy {
    eachDependency { DependencyResolveDetails details ->
        if (details.requested.group == 'com.google.collections' &&
            details.requested.module == 'google-collections') {
            details.useVersion = '1.0'
        }
    }
}
```

Or even select another dependency (guava replaces google-collections dependency)

```
configuration.compile.resolutionStrategy {
    eachDependency { DependencyResolveDetails details ->
        if (details.requested.group == 'com.google.collections' &&
            details.requested.module == 'google-collections') {
            details.useTarget = 'com.google.guava:guava:19.0'
        }
    }
}
```


Demo 6 - Replace bad version
-

Suppose grocery use guava 14.0, which is bad, we want to replace that specific version with 14.0.1 

```
configuration.compile.resolutionStrategy {
    eachDependency { DependencyResolveDetails details ->
        if (details.requested.group == 'com.google.guava' &&
            details.requested.module == 'guava' &&
            details.requested.version == '14.0') {
            details.useVersion = '14.0.1'
        }
    }
}
```

or fail when a bad version is used


```
task validateDeps {
    doLast {
        def badVersions = []
        configurations.compile.incoming.resolutionResult.allModuleVersions {
            if (it.id.group == 'com.google.guava' && it.id.name == 'guava' && it.id.version == '13.0') {
                badVersions << it.id
            }
        }
        
        if (!badVersions.empty) {
            throw new GradleException("You are using bad versions of dependencies: $badVersions")
        }
    }
}

```


Demo 7 - add dependency substitution rules 
-

```

configurations.all {
  resolutionStrategy.dependencySubstitution {
    // Substitute project and module dependencies
    substitute module('org.gradle:api') with project(':api')
    substitute project(':util') with module('org.gradle:util:3.0')

    // Substitute one module dependency for another
    substitute module('org.gradle:api:2.0') with module('org.gradle:api:2.1')
  }
}
```