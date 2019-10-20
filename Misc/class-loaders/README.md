See https://www.baeldung.com/java-classloaders

To test CustomClassLoader:
* Compile the project;
* Move `ClassWithStaticVariable.class` to `C:\dev\toilet\classes\present\programmer\misc\classloader`;
* Delete `ClassWithStaticVariable` class from the project;
* Run and see that different class loaders loaded different instance of classes.