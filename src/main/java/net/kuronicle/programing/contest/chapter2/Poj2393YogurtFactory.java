package net.kuronicle.programing.contest.chapter2;

import java.util.Scanner;

/**
 * <h2>Description</h2>
 * <p>
 * The cows have purchased a yogurt factory that makes world-famous Yucky
 * Yogurt. Over the next N (1 <= N <= 10,000) weeks, the price of milk and labor
 * will fluctuate weekly such that it will cost the company C_i (1 <= C_i <=
 * 5,000) cents to produce one unit of yogurt in week i. Yucky's factory, being
 * well-designed, can produce arbitrarily many units of yogurt each week.
 * 
 * Yucky Yogurt owns a warehouse that can store unused yogurt at a constant fee
 * of S (1 <= S <= 100) cents per unit of yogurt per week. Fortuitously, yogurt
 * does not spoil. Yucky Yogurt's warehouse is enormous, so it can hold
 * arbitrarily many units of yogurt.
 * 
 * Yucky wants to find a way to make weekly deliveries of Y_i (0 <= Y_i <=
 * 10,000) units of yogurt to its clientele (Y_i is the delivery quantity in
 * week i). Help Yucky minimize its costs over the entire N-week period. Yogurt
 * produced in week i, as well as any yogurt already in storage, can be used to
 * meet Yucky's demand for that week.
 * </p>
 * 
 * <h2>Input</h2>
 * <ul>
 * <li>Line 1: Two space-separated integers, N and S.</li>
 * <li>Lines 2..N+1: Line i+1 contains two space-separated integers: C_i and
 * Y_i.</li>
 * </ul>
 * 
 * <h2>Output</h2>
 * <ul>
 * <li>Line 1: Line 1 contains a single integer: the minimum total cost to
 * satisfy the yogurt schedule. Note that the total might be too large for a
 * 32-bit integer.</li>
 * </ul>
 * 
 * <h2>Sample Input</h2>
 * <pre>
 * 4 5
 * 88 200
 * 89 400
 * 97 300
 * 91 500
 * </pre>
 * 
 * <h2>Sample Output</h2>
 * <pre>
 * 126900
 * </pre>
 * 
 */
public class Poj2393YogurtFactory {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();

        int[] c = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            Scanner inForCY = new Scanner(System.in);
            c[i] = inForCY.nextInt();
            y[i] = inForCY.nextInt();
        }
        
        int answer = solve(n, s, c, y);
        
        System.out.println(answer);
    }

    public static int solve(int n, int s, int[] c, int[] y) {
        int totalCost = 0;
        
        for(int i = 0; i < n; i++) {
            int costOfWeekI = calcMinimumCostOfWeek(i, s, c, y);
            totalCost += costOfWeekI;
        }
        
        return totalCost;
    }

    private static int calcMinimumCostOfWeek(int i, int s, int[] c, int[] y) {
        int minCost = Integer.MAX_VALUE;
        
        for (int j = 0; j <= i; j++) {
            int costOfMadeInWeekJ = y[i] * (c[j] + s * (i-j));
            if(minCost > costOfMadeInWeekJ) minCost = costOfMadeInWeekJ;
        }
        
        return minCost;
    }
}
