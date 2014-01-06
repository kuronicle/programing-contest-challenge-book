package programing.contest.chapter2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * <h2>Description</h2>
 * 
 * <p>
 * To avoid unsightly burns while tanning, each of the C (1 ≤ C ≤ 2500) cows
 * must cover her hide with sunscreen when they're at the beach. Cow i has a
 * minimum and maximum SPF rating (1 ≤ minSPFi ≤ 1,000; minSPFi ≤ maxSPFi ≤
 * 1,000) that will work. If the SPF rating is too low, the cow suffers sunburn;
 * if the SPF rating is too high, the cow doesn't tan at all........
 * </p>
 * <p>
 * The cows have a picnic basket with L (1 ≤ L ≤ 2500) bottles of sunscreen
 * lotion, each bottle i with an SPF rating SPFi (1 ≤ SPFi ≤ 1,000). Lotion
 * bottle i can cover coveri cows with lotion. A cow may lotion from only one
 * bottle.
 * </p>
 * <p>
 * What is the maximum number of cows that can protect themselves while tanning
 * given the available lotions?
 * </p>
 * 
 * <h2>Input</h2>
 * 
 * <ul>
 * <li>Line 1: Two space-separated integers: C and L</li>
 * <li>Lines 2..C+1: Line i describes cow i's lotion requires with two integers:
 * minSPFi and maxSPFi</li>
 * <li>Lines C+2..C+L+1: Line i+C+1 describes a sunscreen lotion bottle i with
 * space-separated integers: SPFi and coveri</li>
 * </ul>
 * 
 * <h2>Output</h2>
 * <p>
 * A single line with an integer that is the maximum number of cows that can be
 * protected while tanning
 * </p>
 * 
 * <h2>Sample Input</h2>
 * 
 * <pre>
 * 3 2
 * 3 10
 * 2 5
 * 1 5
 * 6 2
 * 4 1
 * </pre>
 * 
 * <h2>Sample Output</h2>
 * 
 * <pre>
 * 2
 * </pre>
 * 
 */
public class Poj3614SunscreenKuronicle {

    static boolean debug = false;

    public static class Cow {
        public int minSpf;
        public int maxSpf;

        public Cow(final int minSpf, final int maxSpf) {
            this.minSpf = minSpf;
            this.maxSpf = maxSpf;
        }

        @Override
        public String toString() {
            return "Cow [minSpf=" + minSpf + ", maxSpf=" + maxSpf + "]";
        }

        public boolean isAvailable(LotionBottle lotionBottle) {
            if (lotionBottle.cover > 0 && minSpf <= lotionBottle.spf && lotionBottle.spf <= maxSpf) {
                return true;
            }
            return false;
        }
    }

    public static class LotionBottle {
        public int spf;
        public int cover;

        public LotionBottle(final int spf, final int cover) {
            this.spf = spf;
            this.cover = cover;
        }

        @Override
        public String toString() {
            return "LotionBottle [spf=" + spf + ", cover=" + cover + "]";
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        int l = in.nextInt();

        List<Cow> cowList = new ArrayList<Cow>();
        for (int i = 0; i < c; i++) {
            int minSpf = in.nextInt();
            int maxSpf = in.nextInt();
            Cow cow = new Cow(minSpf, maxSpf);
            cowList.add(cow);
        }

        List<LotionBottle> lotionBottleList = new ArrayList<LotionBottle>();
        for (int i = 0; i < l; i++) {
            int spf = in.nextInt();
            int cover = in.nextInt();
            LotionBottle lotionBottle = new LotionBottle(spf, cover);
            lotionBottleList.add(lotionBottle);
        }

        int answer = solve(cowList, lotionBottleList);

        System.out.println(answer);
    }

    public static int solve(List<Cow> cowList, List<LotionBottle> lotionBottleList) {

        // CowをminSpf、maxSpf間が小さい順にソートする
        Collections.sort(cowList, new Comparator<Cow>() {
            @Override
            public int compare(Cow o1, Cow o2) {
                if ((o1.maxSpf-o1.minSpf) > (o2.maxSpf-o2.minSpf)) {
                    return 1;
                }
                if ((o1.maxSpf-o1.minSpf) < (o2.maxSpf-o2.minSpf)) {
                    return -1;
                }
                return 0;
            }
        });
        if (debug) {
            System.out.println(cowList);
        }

        // LotionButtolをspfの降順にソートする
        Collections.sort(lotionBottleList, new Comparator<LotionBottle>() {
            @Override
            public int compare(LotionBottle o1, LotionBottle o2) {
                if (o1.spf < o2.spf) {
                    return 1;
                }
                if (o1.spf > o2.spf) {
                    return -1;
                }
                return 0;
            }
        });
        if (debug) {
            System.out.println(lotionBottleList);
        }

        int coverdCowsNum = 0;
        for (int i = 0; i < lotionBottleList.size(); i++) {
            LotionBottle lotionBottle = lotionBottleList.get(i);

            Iterator<Cow> iCow = cowList.iterator();
            while (iCow.hasNext()) {
                Cow cow = iCow.next();

                if (cow.isAvailable(lotionBottle)) {
                    if (debug) {
                        System.out.println(lotionBottle + " => " + cow);
                    }
                    lotionBottle.cover--;
                    coverdCowsNum++;
                    iCow.remove();
                }

                if (lotionBottle.cover == 0) {
                    break;
                }
            }
            
            if(cowList.size() == 0) {
                break;
            }
        }

        return coverdCowsNum;
    }
}
