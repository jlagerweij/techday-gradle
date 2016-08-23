Incremental build
-

1. clean build -> everything is compiled
2. Remove property from Person
3. Remove comment from Person, see what is up-to-date
4. Change sourceCompatibility to '1.8'
5. Change version junit to 4.8
6. Change the output, remove the Person.class from the build directory
7. Use inputs and outputs for a custom task

```
task generateMountainFiles << {
    def outputDir = mkdir("$buildDir/mountains")
    def mountains = new XmlSlurper().parse(file("src/mountains.xml))
    mountains.mountain.each { mountain -> 
        def mountainFile = new File(outputDir, mountain.name.text())
        mountainFile.text = "feet:${mountain.height.text()}"
    }
}
```

Or as sharable custom task:

```
task generateMountainFiles (type: ConvertMountainXml) {
    outputDir = "$buildDir/mountains" as File
    xmlFile = file("src/mountains.xml")
    seperator = ":"
}

class ConvertMountainXml extends DefaultTask {
    @InputFile
    File xmlFile
    
    @Input 
    String seperator
    
    @OutputDirectory
    File outputDir
    
    @TaskAction
    public void generate() {
        mountains.mountain.each { mountain -> 
            def mountainFile = new File(outputDir, mountain.name.text())
            mountainFile.text = "feet$seperator${mountain.height.text()}"
        }
    }
}
```

8. Change xml
9. Change seperator
10. Use a file that does not exist. You get nice error message.
11. You also get cleanGenerateMountainFiles to remove output of a task
