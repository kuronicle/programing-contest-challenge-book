package net.kuronicle.programing.contest.chapter2;

import java.util.Arrays;

/**
 * p.34 部分和問題
 *
 */
public class BubunwaMondai {

    public static boolean solve(int n, int[] a, int k) {
        
        return addAndCheck(0, a, k);
        
    }
    
    private static boolean addAndCheck(int sum, int[] a, int k) {
        if(sum == k) return true;
        
        if(a.length == 0) return false;
        
        if(addAndCheck(sum, Arrays.copyOfRange(a, 1, a.length),k)) {
            return true;
        }
        
        sum += a[0];
        
        return addAndCheck(sum, Arrays.copyOfRange(a, 1, a.length), k);
    }
    
    
}
