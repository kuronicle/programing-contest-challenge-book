package net.kuronicle.programing.contest.chapter2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class LakeCountingTest {

    @Test
    public void test() {
        String[][] garden = {
                {"W",".",".",".",".",".",".",".",".","W","W","."},
                {".","W","W","W",".",".",".",".",".","W","W","W"},
                {".",".",".",".","W","W",".",".",".","W","W","."},
                {".",".",".",".",".",".",".",".",".","W","W","."},
                {".",".",".",".",".",".",".",".",".","W",".","."},
                {".",".","W",".",".",".",".",".",".","W",".","."},
                {".","W",".","W",".",".",".",".",".","W","W","."},
                {"W",".","W",".","W",".",".",".",".",".","W","."},
                {".","W",".","W",".",".",".",".",".",".","W","."},
                {".",".","W",".",".",".",".",".",".",".","W","."},
        };
        
        LakeCounting counting = new LakeCounting(garden);
        assertThat(counting.countLake(), is(3));
    }

}
