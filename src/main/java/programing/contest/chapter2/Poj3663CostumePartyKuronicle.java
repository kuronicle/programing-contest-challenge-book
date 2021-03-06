package programing.contest.chapter2;

import java.util.Scanner;

/**
 * <h2>Description</h2>
 * <p>
 * It's Halloween! Farmer John is taking the cows to a costume party, but
 * unfortunately he only has one costume. The costume fits precisely two cows
 * with a length of S (1 ≤ S ≤ 1,000,000). FJ has N cows (2 ≤ N ≤ 20,000)
 * conveniently numbered 1..N; cow i has length Li (1 ≤ Li ≤ 1,000,000). Two
 * cows can fit into the costume if the sum of their lengths is no greater than
 * the length of the costume. FJ wants to know how many pairs of two distinct
 * cows will fit into the costume.
 * </p>
 * 
 * <h2>Input</h2>
 * <ul>
 * <li>Line 1: Two space-separated integers: N and S</li>
 * <li>Lines 2..N+1: Line i+1 contains a single integer: Li</li>
 * </ul>
 * 
 * <h2>Output</h2>
 * <ul>
 * <li>Line 1: A single integer representing the number of pairs of cows FJ can
 * choose. Note that the order of the two cows does not matter.</li>
 * </ul>
 * 
 * <h2>Sample Input</h2>
 * <pre>
 * 4 6
 * 3
 * 5
 * 2
 * 1
 * </pre>
 * 
 * <h2>Sample Output</h2>
 * <pre>
 * 4
 * </pre>
 * 
 */
public class Poj3663CostumePartyKuronicle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int s = in.nextInt();

        int[] l = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = in.nextInt();
        }
        
        int answer = solve(n, s, l);

        System.out.println(answer);
    }

    public static int solve(int n, int s, int[] l) {
        return countPaires(s, l);
    }

    private static int countPaires(int s, int[] l) {
        return countPairs(s, l, 0);
    }

    private static int countPairs(int s, int[] l, int i) {
        if (l.length == i - 1)
            return 0;

        int countOfPaires = 0;

        for (int j = i + 1; j < l.length; j++) {
            int sumOfLength = l[i] + l[j];
            if (sumOfLength <= s)
                countOfPaires++;
        }

        int countOfPairsNextI = countPairs(s, l, i + 1);

        return countOfPaires + countOfPairsNextI;
    }

}
