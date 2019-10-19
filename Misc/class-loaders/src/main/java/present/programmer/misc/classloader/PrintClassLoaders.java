package present.programmer.misc.classloader;

import com.sun.javafx.binding.Logging;

import java.util.ArrayList;

public class PrintClassLoaders {

    private static final boolean INITIALIZE = true;

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Class loader of this class: " + PrintClassLoaders.class.getClassLoader());
        System.out.println("Class loader of Logging class: " + Logging.class.getClassLoader());
        System.out.println("Class loader of ArrayList class: " + ArrayList.class.getClassLoader());
        System.out.println(System.getProperty("java.ext.dirs"));
        java.lang.Class.forName("present.programmer.misc.classloader.ClassWithStaticVariable",
                INITIALIZE, PrintClassLoaders.class.getClassLoader());
    }
}
