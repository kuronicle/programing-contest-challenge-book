package net.kuronicle.programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class Poj3663CostumePartyTest {

    @Test
    public void solveTest() {
        int n = 4;
        int s = 6;
        int[] l = {3, 5, 2, 1};
        int expected = 4;
        
        int actual = Poj3663CostumeParty.solve(n, s, l);
        
        assertThat(actual, is(expected));
    }

}
