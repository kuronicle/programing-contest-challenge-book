package programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class Poj3669MeteorShowerKuronicleTest {

    @Test
    public void solveTest() {
        Poj3669MeteorShowerKuronicle.debug = true;
        
        int[][] map = Poj3669MeteorShowerKuronicle.initMap();

        Poj3669MeteorShowerKuronicle.fallMeteor(map, 0, 0, 2);
        Poj3669MeteorShowerKuronicle.fallMeteor(map, 2, 1, 2);
        Poj3669MeteorShowerKuronicle.fallMeteor(map, 1, 1, 2);
        Poj3669MeteorShowerKuronicle.fallMeteor(map, 0, 3, 5);
        
        int expected = 5;

        int actual = Poj3669MeteorShowerKuronicle.solve(map);

        assertThat(actual, is(expected));
    }

}
