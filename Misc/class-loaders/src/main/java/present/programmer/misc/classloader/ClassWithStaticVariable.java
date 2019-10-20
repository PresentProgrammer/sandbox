package present.programmer.misc.classloader;

import java.util.Date;

public class ClassWithStaticVariable {

    public static String myName = initMyName();

    private static String initMyName() {
        final String myName = "I was loaded by " + ClassWithStaticVariable.class.getClassLoader().toString();
        // throw new RuntimeException(); -- will result in ExceptionInInitializerError.
        System.out.println(myName);
        return myName;
    }
}
