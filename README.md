# Project-42

Project 42 is a simple Manual API with a ton of customization features that will allow you to change everything so it looks unique and without needing to handle all the GUI stuff.

## How to start

### Gradle Dependency

```
repositories {
    maven {
        name 'Curseforge Maven'
        url 'http://minecraft.curseforge.com/api/maven/'
    }
}

dependencies {
    compile "project-42:project42-1.12.2:1.0.0:2:deobf"
}
```
(Or the latest version in curseforge)

### Project 42

To start using the mod you will need a class that implements `IManual` with the annotation `@ProjectManual`:

```
@ProjectManual(value = Project42.MOD_ID, modName = Project42.MOD_NAME)
public class TestManual implements IManual {

    @Override
    public void registerCategories(ManualInfo info) {

    }

}
```

After that you can register all the categories and all of the entries with their contents inside `registerCategories`. You can find examples:
* A simple [example](https://github.com/Buuz135/Project-42/blob/master/src/test/java/TestManual.java) without custom design
* A bit more advanced [example](https://github.com/Buuz135/Project-42/blob/master/src/test/java/TestManualCustomDesign.java) with a custom design 
