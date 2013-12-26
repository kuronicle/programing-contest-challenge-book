package programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class Poj2393YogurtFactoryKuronicleTest {

    @Test
    public void solveTest() {
        int n = 4;
        int s = 5;
        int[] c = {88, 89, 97, 91};
        int[] y = {200, 400, 300, 500};
        
        long expected = 126900;
        
        long actual = Poj2393YogurtFactoryKuronicle.solve(n, s, c, y);
        
        assertThat(actual, is(expected));
    }

}
