import org.junit.Test;

import java.util.Arrays;

public class SomeTests {

    @Test
    public void m() {
        System.out.println(Arrays.toString("dir\\n\\tsubdir1\\n\\tsubdir2\\n\\t\\tfile.ext".split("\\\\n")));
        System.out.println(Arrays.toString("dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext".split("\\\\n")));
    }
}
