package programing.contest.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Poj2139SixDegreesofCowvinBaconKesota826 {
	static Date start;
	static Date end;
	static long time;

	static int count = 0;

	static int[][] map;
	static int[] d;

	static final int INF = 100000;

	public static void main(String[] args) throws FileNotFoundException {
		// 開始時の時間
		start = new Date();

		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
		//		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		// 距離マップの初期化
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (i == j) {
					map[i][j] = 0;
				} else {
					map[i][j] = INF;
				}
			}
		}

		for (int i=0; i<M; i++) {
			int C = sc.nextInt();

			List<Integer> list = new ArrayList<Integer>();

			for (int j=0; j<C; j++) {
				list.add(sc.nextInt());
			}

			for (int j=0; j<list.size()-1; j++) {
				for (int k=j+1; k<list.size(); k++) {
					map[list.get(j)-1][list.get(k)-1] = 1;
					map[list.get(k)-1][list.get(j)-1] = 1;
				}
			}
		}

		int ans = INF;
		int tmp = 0;

		// ワーシャル・フロイデ法
//		for (int k=0; k<N; k++) {
//			for (int i=0; i<N; i++) {
//				for (int j=0; j<N; j++) {
//
//					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
//
//				}
//			}
//		}
//
//		for (int i=0; i<N; i++) {
//			tmp = 0;
//			for (int j=0; j<N; j++) {
//				tmp += map[i][j];
//			}
//
//			if (tmp < ans) {
//				ans = tmp;
//			}
//		}



		// ダイクストラ法


		Vec v = null;
		int node;
		int dis;

		Queue<Vec> q = new PriorityQueue<Vec>(1500, new OriginalComparator());

		for (int i=0; i<N; i++) {
			// 距離マップをd[i]だけ0で後はINFで初期化
			d = new int[N];
			for (int j=0; j<N; j++) {
				d[j] = INF;
			}
			d[i] = 0;

			// Cow[i]から全Cowへの距離を測定
			node = i;
			dis = 0;

			q.add(new Vec(node,dis));

			while(!q.isEmpty()) {
				v = q.poll();
				node = v.a;
				dis = v.b;

				if (d[node] < dis) {
					continue;
				}

				for (int j=0; j<N; j++) {
					if (d[j] > d[node] + map[node][j]) {
						d[j] =d[node] + map[node][j];
						q.add(new Vec(j, d[j]));
					}
				}
			}

			tmp = 0;
			for (int j=0; j<N; j++) {
				tmp += d[j];
			}
			if (tmp < ans) {
				ans = tmp;
			}

		}


		ans = ans*100/(N-1);

		System.out.println(ans);

	}

	public static class Vec {
		public int a;
		public int b;

		public Vec(int a, int b) {
			this.a = a;
			this.b = b;
		}
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