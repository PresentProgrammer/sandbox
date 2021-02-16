import org.junit.Test;

import java.util.TreeMap;

public class SomeTests {

    @Test
    public void treeMap_subMap_clear() {
        final TreeMap<Integer, Boolean> treeMap = new TreeMap<>();
        treeMap.put(1, true);
        treeMap.put(4, true);
        treeMap.put(6, true);
        treeMap.put(10, true);
        treeMap.subMap(4, 7).clear();
        System.out.println(treeMap);
    }
}
