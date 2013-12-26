package programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class Poj3669MeteorShowerKuronicleTest {

    @Test
    public void solveTest() {
        int m = 4;
        int[] x = {0, 2, 1, 0};
        int[] y = {0, 1, 1, 3};
        int[] t = {2, 2, 2, 5};
        
        int expected = 5;
        
        int actual = Poj3669MeteorShowerKuronicle.solve(m, x, y, t);
        
        assertThat(actual, is(expected));
    }

}
