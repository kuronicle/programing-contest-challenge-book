package programing.contest.chapter2;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Poj3176CowBowlingKesota826 {

	static int n;
	static int[][] map;
	static int[][] ans;

	static final int INF = 1000000;

	public static void main(String[] args) throws IOException {
		// 構造体の代わり
		class Point {
			public int x;
			public int y;
			public int t;

			public Point(int x, int y, int t) {
				this.x = x;
				this.y = y;
				this.t = t;
			}
		}


		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
//		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		map = new int[n][n];
		ans = new int[n][n];

		for (int i=0; i<n; i++) {
			for (int j=0; j<i+1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 各地点の隕石到達時刻を調べる
		for (int i=n-1; i>=0; i--) {
			for (int j=0; j<i+1; j++) {
			if (i == n-1) {
				ans[i][j] = map[i][j];
			} else {
				ans[i][j] = Math.max(ans[i+1][j],ans[i+1][j+1])+map[i][j];
			}
			}
		}

		System.out.println(ans[0][0]);
	}
}