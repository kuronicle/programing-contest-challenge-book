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
 * <li>Result: Accepted</li>
 * <li>Memory: 5952KB</li>
 * <li>Time: 3704ms</li>
 * </ul>
 * 
 */
public class Poj3669MeteorShowerKuronicle {

    /**
     * マップサイズ：X座標。
     */
    private static final int MAP_SIZE_X = 400;

    /**
     * マップサイズ：Y座標。
     */
    private static final int MAP_SIZE_Y = 400;

    /**
     * スタート位置：X座標。
     */
    private static final int START_POINT_X = 0;

    /**
     * スタート位置：Y座標。
     */
    private static final int START_POINT_Y = 0;

    /**
     * 隕石の影響範囲。
     */
    private static final int[][] METEOR_AFFECT_AREA = { { 0, 0, 1, 0, -1 }, { 0, 1, 0, -1, 0 } };

    /**
     * Bessieが移動する方向。
     */
    private static final int[][] MOVE_VECTOR = { { 0, 1, 0, -1 }, { 1, 0, -1, 0 } };

    /**
     * デバッグログ出力フラグ。
     */
    public static boolean debug = false;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();

        int[][] map = initMap();

        for (int i = 0; i < m; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int t = in.nextInt();
            fallMeteor(map, x, y, t);
        }

        int answer = solve(map);

        System.out.println(answer);
    }

    /**
     * マップを初期化する。
     * 
     * @return 初期化したマップ。
     */
    public static int[][] initMap() {
        int[][] map = new int[MAP_SIZE_X][MAP_SIZE_Y];
        for (int i = 0; i < MAP_SIZE_X; i++) {
            for (int j = 0; j < MAP_SIZE_Y; j++) {
                map[i][j] = -1;
            }
        }
        return map;
    }

    /**
     * マップに隕石を落とす。
     * <ul>
     * <li>mapは予め-1で初期化されていることを前提とする。</li>
     * <li>隕石が初めて落ちた秒数をmapの値に設定する。</li>
     * </ul>
     * 
     * @param map
     *            マップ。メソッド内で更新される。
     * @param x
     *            隕石が落ちるX座標。
     * @param y
     *            隕石が落ちるY座業。
     * @param t
     *            隕石が落ちる秒数。
     */
    public static void fallMeteor(int[][] map, final int x, final int y, final int t) {
        for (int i = 0; i < METEOR_AFFECT_AREA[0].length; i++) {
            int affect_x = x + METEOR_AFFECT_AREA[0][i];
            int affect_y = y + METEOR_AFFECT_AREA[1][i];
            if (isValidArea(affect_x, affect_y)) {
                if (map[affect_x][affect_y] == -1 || map[affect_x][affect_y] > t) {
                    map[affect_x][affect_y] = t;
                }
            }
        }
    }

    /**
     * 安全地帯（-1）に到着するまで移動し、到着秒数を返却する。
     * @param map マップ。メソッド内で更新される。
     * @return 到着秒数。到着できなかった場合は-1を返却する。
     */
    public static int solve(int[][] map) {
        Queue<Point> queue = new LinkedList<Point>();
        Point startPoint = new Point(START_POINT_X, START_POINT_Y, map[START_POINT_X][START_POINT_Y], 0);
        if (!startPoint.canLocate()) {
            return -1;
        }
        if (startPoint.isSafePoint()) {
            return startPoint.arrivedTime;
        }
        queue.add(startPoint);
        map[startPoint.getX()][startPoint.getY()] = startPoint.arrivedTime;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (debug) {
                System.out.println(point);
            }

            // 移動できる場所がある場合は移動する。
            for (int i = 0; i < MOVE_VECTOR[0].length; i++) {
                int next_x = point.getX() + MOVE_VECTOR[0][i];
                int next_y = point.getY() + MOVE_VECTOR[1][i];
                int next_t = point.getArrivedTime() + 1;
                if (!isValidArea(next_x, next_y)) {
                    continue;
                }

                Point nextPoint = new Point(next_x, next_y, map[next_x][next_y], next_t);

                // 安全地帯であれば終了。
                if (nextPoint.isSafePoint()) {
                    if (debug) {
                        System.out.println("Arrived at a safe point. point=" + nextPoint.toString());
                    }
                    return nextPoint.getArrivedTime();
                }

                // 次のポイントに移動できる場合はqueueに追加する。
                if (nextPoint.canLocate()) {
                    queue.add(nextPoint);
                    // 再度同じ場所に到着しないように、到着秒数でマップを更新する。
                    map[next_x][next_y] = next_t;
                }
            }
        }

        // 到着できなかった場合は-1を返却。
        return -1;
    }

    private static boolean isValidArea(final int x, final int y) {
        return 0 <= x && x < MAP_SIZE_X && 0 <= y && y < MAP_SIZE_Y;
    }

    private static class Point {
        /**
         * X座標。
         */
        private int x;
        
        /**
         * Y座標。
         */
        private int y;
        
        /**
         * 隕石が落ちる秒数。
         */
        private int meteorFallTime;
        
        /**
         * 到着秒数。
         */
        private int arrivedTime;

        public Point(int x, int y, int meteorFallTime, int arrivedTime) {
            this.x = x;
            this.y = y;
            this.meteorFallTime = meteorFallTime;
            this.arrivedTime = arrivedTime;
        }

        /**
         * 移動できる場所かどうかを確認する。
         * @return true: 移動できる。false: 移動できない。
         */
        private boolean canLocate() {
            if (isSafePoint()) {
                return true;
            }
            return arrivedTime < meteorFallTime;
        }

        /**
         * 安全地帯かどうかを確認する。
         * @return true: 安全地帯。false: 安全地帯でない。
         */
        private boolean isSafePoint() {
            return meteorFallTime == -1;
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

        @Override
        public String toString() {
            return "Point [x=" + x + ", y=" + y + ", meteorFallTime=" + meteorFallTime + ", arrivedTime=" + arrivedTime + "]";
        }
    }
}
