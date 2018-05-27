package present.programmer.algorithms.sandbox.sort.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MedianTest {

    private static final Integer MEDIAN = 20;
    private static final Integer MIN = 10;
    private static final Integer MAX = 30;
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int LAST = 2;

    @Test
    public void findMedian_betweenDistinctElements_expectedIndexIsReturned() {
        assertThat(medianOf(MEDIAN, MIN, MAX), is(FIRST));
        assertThat(medianOf(MEDIAN, MAX, MIN), is(FIRST));
        assertThat(medianOf(MIN, MEDIAN, MAX), is(SECOND));
        assertThat(medianOf(MAX, MEDIAN, MIN), is(SECOND));
        assertThat(medianOf(MIN, MAX, MEDIAN), is(LAST));
        assertThat(medianOf(MAX, MIN, MEDIAN), is(LAST));
    }

    @Test
    public void findMedian_whenElementsDuplicate_expectedIndexIsReturned() {
        assertThat(medianOf(MEDIAN, MEDIAN, MIN), either(is(FIRST)).or(is(SECOND)));
        assertThat(medianOf(MEDIAN, MEDIAN, MAX), either(is(FIRST)).or(is(SECOND)));
        assertThat(medianOf(MEDIAN, MIN, MEDIAN), either(is(FIRST)).or(is(LAST)));
        assertThat(medianOf(MEDIAN, MAX, MEDIAN), either(is(FIRST)).or(is(LAST)));
        assertThat(medianOf(MIN, MEDIAN, MEDIAN), either(is(SECOND)).or(is(LAST)));
        assertThat(medianOf(MAX, MEDIAN, MEDIAN), either(is(SECOND)).or(is(LAST)));
        assertThat(medianOf(MEDIAN, MEDIAN, MEDIAN), either(is(FIRST)).or(is(SECOND)).or(is(LAST)));
    }

    // Auxiliary Methods

    private int medianOf(final Integer... elements) {
        return Median.of3(elements, FIRST, SECOND, LAST);
    }
}