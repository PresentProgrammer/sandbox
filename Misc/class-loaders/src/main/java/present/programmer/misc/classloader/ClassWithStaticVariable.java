package present.programmer.misc.classloader;

class ClassWithStaticVariable {

    private static String myName = initMyName();

    private static String initMyName() {
        final String myName = "PresentProgrammer";
//        throw new RuntimeException();
        System.out.println(myName);
        return myName;
    }
}
