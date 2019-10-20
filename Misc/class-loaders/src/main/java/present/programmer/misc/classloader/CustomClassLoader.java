package present.programmer.misc.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    @Override
    public Class findClass(final String name) throws ClassNotFoundException {
        final byte[] bytes = loadClassFromFile(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassFromFile(final String name) {
        try (final InputStream is = getClass().getClassLoader().getResourceAsStream(
                name.replace('.', File.separatorChar) + ".class")) {
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int nextVal = 0;
            while ((nextVal = is.read()) != -1) {
                bos.write(nextVal);
            }
            return bos.toByteArray();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "PresentProgrammer Enterprises Class Loader";
    }

    public static void main(String[] args) throws Exception {
        final CustomClassLoader customClassLoader = new CustomClassLoader();
        System.out.println(customClassLoader.getParent());

        final Class foundByCustomWithoutDelegation = customClassLoader.findClass(
                "present.programmer.misc.classloader.ClassWithStaticVariable");
        final Class loadedByDef = PrintClassLoaders.class.getClassLoader().loadClass("present.programmer.misc.classloader.ClassWithStaticVariable");
        System.out.println(foundByCustomWithoutDelegation.getClassLoader());
        System.out.println(loadedByDef.getClassLoader());
        System.out.println("classes are not the same: " + (foundByCustomWithoutDelegation == loadedByDef));

        foundByCustomWithoutDelegation.newInstance();
        loadedByDef.newInstance();

        System.out.println(foundByCustomWithoutDelegation.getField("myName").get(null));
        System.out.println(loadedByDef.getField("myName").get(null));
    }
}
