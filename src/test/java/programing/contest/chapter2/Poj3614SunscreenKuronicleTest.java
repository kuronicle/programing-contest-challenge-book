package programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static programing.contest.chapter2.Poj3614SunscreenKuronicle.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Poj3614SunscreenKuronicleTest {

    @Test
    public void solveTest() {
        
        Poj3614SunscreenKuronicle.debug = true;
        
        List<Cow> cowList = new ArrayList<Cow>();
        cowList.add(new Cow(3, 10));
        cowList.add(new Cow(2, 5));
        cowList.add(new Cow(1, 5));
        
        List<LotionBottle> lotionBottleList = new ArrayList<LotionBottle>();
        lotionBottleList.add(new LotionBottle(6, 2));
        lotionBottleList.add(new LotionBottle(4, 1));
        
        int actual = Poj3614SunscreenKuronicle.solve(cowList, lotionBottleList);
        
        int expected = 2;
        
        assertThat(actual, is(expected));
    }
    
    @Test
    public void solveTest2() {
        
        Poj3614SunscreenKuronicle.debug = true;
        
        List<Cow> cowList = new ArrayList<Cow>();
        cowList.add(new Cow(3, 10));
        cowList.add(new Cow(5, 6));
        
        List<LotionBottle> lotionBottleList = new ArrayList<LotionBottle>();
        lotionBottleList.add(new LotionBottle(6, 1));
        lotionBottleList.add(new LotionBottle(4, 1));
        
        int actual = Poj3614SunscreenKuronicle.solve(cowList, lotionBottleList);
        
        int expected = 2;
        
        assertThat(actual, is(expected));
    }


}
