package programing.contest.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class Poj2429GCDandLCMInverseKesota826 {
	static Date start;
	static Date end;
	static long time;

	static final long INF = 1000000;

	public static void main(String[] args) throws FileNotFoundException {
		// 開始時の時間
		start = new Date();

		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
		//		Scanner sc = new Scanner(System.in);

		while (sc.hasNextInt()) {
			long gcd = sc.nextLong();
			long lcm = sc.nextLong();
			solve(gcd, lcm);
		}
	}

	public static void solve (long gcd, long lcm) {
		long ab = lcm/gcd;

		long sqrt = (long) Math.sqrt(ab);

		for (long a = sqrt; a>0; a--) {
			long b = ab/a;
			if ((ab - a*b) == 0 && gcd(a,ab/a) == 1) {
				System.out.println(a*gcd + " " + b*gcd);
				return;
			}
		}
	}

	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd (b, a%b);
	}
}