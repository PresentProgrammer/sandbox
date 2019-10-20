See https://www.baeldung.com/java-classloaders

To see how the same class can be loaded multiple times:
* Compile the project;
* Move `ClassWithStaticVariable.class` to `C:\dev\toilet\classes\present\programmer\misc\classloader` — so that the default Application Class Loader could not find it in classpath.
* Delete `ClassWithStaticVariable` class from the project;
* Run `CustomClassLoader.main()` and see that different class loaders loaded the same class twice.