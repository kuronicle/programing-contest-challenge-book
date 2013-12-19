package net.kuronicle.programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class BubunwaMondaiTest {

    @Test
    public void test1() {
        int n = 4;
        int[] a = {1,2,4,7};
        int k = 13;
        assertThat(BubunwaMondai.solve(n, a, k), is(true));
    }

    @Test
    public void test2() {
        int n = 4;
        int[] a = {1,2,4,7};
        int k = 15;
        assertThat(BubunwaMondai.solve(n, a, k), is(false));
    }
}
