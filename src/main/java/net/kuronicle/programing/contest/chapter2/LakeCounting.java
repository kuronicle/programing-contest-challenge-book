package net.kuronicle.programing.contest.chapter2;

public class LakeCounting {

    String[][] garden;

    public LakeCounting(String[][] garden) {
        this.garden = garden;
    }

    public int countLake() {
        int lakeNum = 0;

        for (int i = 0; i < garden.length; i++) {
            for (int j = 0; j < garden[0].length; j++) {
                if (isCountable(i, j))
                    lakeNum++;
            }
        }
        return lakeNum;
    }

    private boolean isCountable(int i, int j) {
        boolean countable = false;
        if ("W".equals(garden[i][j])) {
            countable = true;
        }
        if ("W".equals(garden[i][j]) || "C".equals(garden[i][j])) {
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    if (i + k >= 0 && i + k < garden.length && j + l >= 0
                            && j + l < garden[0].length
                            && "W".equals(garden[i + k][j + l])) {
                        garden[i + k][j + l] = "C";
                    }
                }
            }
        }
        return countable;
    }
}
