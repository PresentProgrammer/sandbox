# Making JVM throw checked exception without `throws` clause

## Follow these steps:
1. In "no-exceptions" folder, run `javac Client.java`.
2. Copy the compiled Client.class into "with-exceptions" folder.
3. In "with-exceptions" folder, run `javac Thrower.java`.
4. Run `java Client`, and see the message from the uncaught checked exception.

Even though in Client's main method there is no `throws` clause, the program worked and exception was thrown. This 
shows that on JVM level it does not matter whether an exception is checked or unchecked.