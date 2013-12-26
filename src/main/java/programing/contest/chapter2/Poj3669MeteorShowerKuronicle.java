package programing.contest.chapter2;

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
 * <li>Lines 2..M+1: Line i+1 contains three
 * space-separated integers: Xi, Yi, and Ti</li>
 * </ul>
 * 
 * <h2>Output</h2>
 * <ul>
 * <li>Line 1: The minimum time it takes Bessie to get to a safe place or -1 if it
 * is impossible.</li>
 * </ul>
 * 
 * <h2>Sample Input</h2>
 * <pre>
 * 4
 * 0 0 2 
 * 2 1 2 
 * 1 1 2 
 * 0 3 5 
 * </pre>
 * 
 * <h2>Sample Output</h2>
 * <pre>
 * 5
 * </pre>
 */
public class Poj3669MeteorShowerKuronicle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        
        int[] x = new int[m];
        int[] y = new int[m];
        int[] t = new int[m];
        for(int i = 0; i < m; i++) {
            x[i] = in.nextInt();
            y[i] = in.nextInt();
            t[i] = in.nextInt();
        }
        
        int answer = solve(m, x, y, t);
        
        System.out.println(answer);
    }

    public static int solve(int m, int[] x, int[] y, int[] t) {
        // TODO Auto-generated method stub
        return 0;
    }
}
