package programing.contest.chapter2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <h2>Description</h2>
 * <p>
 * Bessie hears that an extraordinary meteor shower is coming; reports say that
 * these meteors will crash into earth and destroy anything they hit. Anxious
 * for her safety, she vows to find her way to a safe location (one that is
 * never destroyed by a meteor) . She is currently grazing at the origin in the
 * coordinate plane and wants to move to a new, safer location while avoiding
 * being destroyed by meteors along her way.
 * </p>
 * <p>
 * The reports say that M meteors (1 ≤ M ≤ 50,000) will strike, with meteor i
 * will striking point (Xi, Yi) (0 ≤ Xi ≤ 300; 0 ≤ Yi ≤ 300) at time Ti (0 ≤ Ti
 * ≤ 1,000). Each meteor destroys the point that it strikes and also the four
 * rectilinearly adjacent lattice points.
 * </p>
 * <p>
 * Bessie leaves the origin at time 0 and can travel in the first quadrant and
 * parallel to the axes at the rate of one distance unit per second to any of
 * the (often 4) adjacent rectilinear points that are not yet destroyed by a
 * meteor. She cannot be located on a point at any time greater than or equal to
 * the time it is destroyed).
 * </p>
 * <p>
 * Determine the minimum time it takes Bessie to get to a safe place.
 * </p>
 * 
 * <h2>Input</h2>
 * <ul>
 * <li>Line 1: A single integer: M</li>
 * <li>Lines 2..M+1: Line i+1 contains three space-separated integers: Xi, Yi,
 * and Ti</li>
 * </ul>
 * 
 * <h2>Output</h2>
 * <ul>
 * <li>Line 1: The minimum time it takes Bessie to get to a safe place or -1 if
 * it is impossible.</li>
 * </ul>
 * 
 * <h2>Sample Input</h2>
 * 
 * <pre>
 * 4
 * 0 0 2 
 * 2 1 2 
 * 1 1 2 
 * 0 3 5
 * </pre>
 * 
 * <h2>Sample Output</h2>
 * 
 * <pre>
 * 5
 * </pre>
 * 
 * <h2>Result</h2>
 * <ul>
 * <li>Result: Memory Limit Exceeded</li>
 * <li>Memory: -KB</li>
 * <li>Time: -ms</li>
 * </ul>
 * 
 */
public class Poj3669MeteorShowerKuronicle {

    private static final int MAP_X_MAX = 301;
    private static final int MAP_Y_MAX = 301;
    private static final int[][] METEOR_AFFECT_AREA = { { 0, 1, 0, -1 }, { 1, 0, -1, 0 } };
    private static final int[][] MOVE_VECTOR = { { 0, 1, 0, -1 }, { 1, 0, -1, 0 } };
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();

        int[][] map = new int[MAP_X_MAX][MAP_Y_MAX];
        
        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int t = in.nextInt();
            fallMeteor(map, x, y, t);
        }

        int answer = solve(map);

        System.out.println(answer);
    }

    public static void fallMeteor(int[][] map, int x, int y, int t) {
            map[x][y] = t;
            for (int j = 0; j < METEOR_AFFECT_AREA[0].length; j++) {
                int affect_x = x + METEOR_AFFECT_AREA[0][j];
                int affect_y = y + METEOR_AFFECT_AREA[1][j];
                if (isValidArea(affect_x, affect_y)) {
                    map[affect_x][affect_y] = t;
                }
            }
    }

    public static int solve(int[][] map) {
        Queue<Point> queue = new LinkedList<Point>();
        Point startPoint = new Poj3669MeteorShowerKuronicle().new Point(0, 0, map[0][0], 0);
        queue.add(startPoint);

        while (queue.isEmpty() == false) {
            Point point = queue.poll();

            if (point.isSafePoint()) {
                return point.getArrivedTime();
            }

            if (point.canLocate()) {
                for (int i = 0; i < MOVE_VECTOR[0].length; i++) {
                    int next_x = point.getX() + MOVE_VECTOR[0][i];
                    int next_y = point.getY() + MOVE_VECTOR[1][i];
                    if (isValidArea(next_x, next_y)) {
                        Point nextPoint = new Poj3669MeteorShowerKuronicle().new Point(next_x, next_y, map[next_x][next_y], point.getArrivedTime()+1);
                        queue.add(nextPoint);
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isValidArea(int i, int j) {
        return 0 <= i && i < MAP_X_MAX && 0 <= j && j < MAP_Y_MAX;
    }

    private class Point {
        private int x;
        private int y;
        private int meteorFallTime;
        private int arrivedTime;
    
        public Point(int x, int y, int meteorFallTime, int arrivedTime) {
            this.x = x;
            this.y = y;
            this.meteorFallTime = meteorFallTime;
            this.arrivedTime = arrivedTime;
        }
    
        boolean canLocate() {
            if (isSafePoint()) {
                return true;
            }
            return arrivedTime < meteorFallTime;
        }
    
        private boolean isSafePoint() {
            return meteorFallTime == 0;
        }
    
        public int getX() {
            return this.x;
        }
    
        public int getY() {
            return this.y;
        }
        
        public int getArrivedTime() {
            return this.arrivedTime;
        }
    }
}
