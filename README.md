Techday Gradle
=

Presentation
-

In directory type ./gradlew to build and view the presentation.

Hands-on
-

1. Convert Maven project into Gradle project.
- Use gradle init.
- Use supplied maven-multi-module-ear project if you don't have your own project.

2. Use com.craigburke.client-dependencies for NPM and Bower dependencies
- See: https://github.com/craigburke/client-dependencies-gradle

3. Create NPM Gradle build using:
- gradle-node-plugin
- gradle-gulp-plugin
- bower-to-prod-gradle-plugin
- gradle-js-plugin

4. Use 'net.diibadaaba.zipdiff:zipdiff:1.0' as Ant task to verify archives

5. Gradle continuous development
- Test Gradle continuous development by running Gradle --continuous.
