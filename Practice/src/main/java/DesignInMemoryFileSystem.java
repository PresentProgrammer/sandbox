import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * Problem #588
 * Time complexity: O()
 * Space complexity: O()
 **/
public class DesignInMemoryFileSystem {

    private final File root = new File("");

    public List<String> ls(String path) {
        return getByPath(path).ls();
    }

    public void mkdir(String path) {
        getByPath(path);
    }

    public void addContentToFile(String path, String content) {
        getByPath(path).appendContent(content);
    }

    public String readContentFromFile(String path) {
        return getByPath(path).getContent();
    }

    private File getByPath(String path) {
        File curr = root;
        for (final String part : getPathParts(path)) {
            curr = curr.getSubDir(part);
        }
        return curr;
    }

    private static String[] getPathParts(String path) {
        return path.equals("/") ? new String[0] : path.substring(1).split("[/]");
    }

    private static class File {

        private final String name;
        private final TreeMap<String, File> children = new TreeMap<>();
        private final StringBuilder contentBuilder = new StringBuilder();

        File(String name) {
            this.name = name;
        }

        List<String> ls() {
            return contentBuilder.length() > 0
                    ? Collections.singletonList(name)
                    : new ArrayList<>(children.keySet());
        }

        File getSubDir(String subDir) {
            return children.computeIfAbsent(subDir, File::new);
        }

        void appendContent(String newContent) {
            contentBuilder.append(newContent);
        }

        String getContent() {
            return contentBuilder.toString();
        }
    }

    public static void main(final String[] args) {
        final DesignInMemoryFileSystem fileSystem = new DesignInMemoryFileSystem();
        System.out.println("[] == " + fileSystem.ls("/"));
        fileSystem.mkdir("/a/b/c");
        fileSystem.addContentToFile("/a/b/c/d", "hello");
        System.out.println("[a] == " + fileSystem.ls("/"));
        System.out.println("hello == " + fileSystem.readContentFromFile("/a/b/c/d"));
    }
}