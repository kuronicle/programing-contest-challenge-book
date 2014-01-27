package programing.contest.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Poj1258AgriNetKesota826 {
	static Date start;
	static Date end;
	static long time;

	static final int INF = 1000000;

	public static void main(String[] args) throws FileNotFoundException {
		// 開始時の時間
		start = new Date();

		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
		//		Scanner sc = new Scanner(System.in);

		while(sc.hasNextInt()) {
			int ans = solve(sc);
			System.out.println(ans);
		}
	}

		public static class Vec {
			public int a;
			public int b;

			public Vec(int a, int b) {
				this.a = a;
				this.b = b;
			}
		}

		static int solve(Scanner sc) {
			int N = sc.nextInt();

			// 距離マップの初期化
			int[][] map = new int[N][N];
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			int ans = 0;

			int[] mincost = new int[N];
			int[] xList = new int[N];

			for (int i=0; i<N; i++) {
				mincost[i] = INF;
				xList[i] = -1;
			}

			mincost[0] = 0;
			Queue<Vec> q = new PriorityQueue<Vec>(200, new OriginalComparator());
			q.add(new Vec(0,0));

			int v;
			// プリム法
			while (q.size() != 0) {
				v = -1;

				v = q.peek().a;


				xList[v] = 1;
				ans += mincost[v];

				q.clear();
				for (int i=0; i<N; i++) {
					if (xList[i] == -1) {
						mincost[i] = Math.min(mincost[i], map[v][i]);
						q.add(new Vec(i,mincost[i]));
					}
				}

			}
			return ans;
		}

		public static class OriginalComparator implements Comparator<Vec> {

			public int compare(Vec v1, Vec v2) {
				// TODO 自動生成されたメソッド・スタブ
				if (v1.b > v2.b) {
					return 1;
				} else if (v1.b == v2.b) {
					return 0;
				} else {
					return -1;
				}
			}

		}

	}