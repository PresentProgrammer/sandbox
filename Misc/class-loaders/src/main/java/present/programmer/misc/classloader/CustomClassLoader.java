package present.programmer.misc.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    private static final String CLASS_NAME = "present.programmer.misc.classloader.ClassWithStaticVariable";

    private final String classLoaderName;

    private CustomClassLoader(final String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }

    @Override
    public Class findClass(final String name) throws ClassNotFoundException {
        final byte[] bytes = loadClassFromFile(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassFromFile(final String name) {
        try (final InputStream is = new FileInputStream(new File("C:/dev/toilet/classes/" +
                name.replace('.', '/') + ".class"))) {
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
        return "Custom class loader with name: " + classLoaderName;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new CustomClassLoader("whatIsMyParent").getParent());

        final Class firstLoadedByCustom = new CustomClassLoader("first").loadClass(CLASS_NAME);
        final Class secondLoadedByCustom = new CustomClassLoader("second").loadClass(CLASS_NAME);
        System.out.println("classes are not the same: " + (firstLoadedByCustom != secondLoadedByCustom));
        System.out.println(firstLoadedByCustom.getField("myName").get(null));
        System.out.println(secondLoadedByCustom.getField("myName").get(null));
    }
}
