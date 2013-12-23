package net.kuronicle.programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class Poj2393YogurtFactoryTest {

    @Test
    public void solveTest() {
        int n = 4;
        int s = 5;
        int[] c = {88, 89, 97, 91};
        int[] y = {200, 400, 300, 500};
        int expected = 126900;
        
        int actual = Poj2393YogurtFactory.solve(n, s, c, y);
        
        assertThat(actual, is(expected));
    }
    
    

}
