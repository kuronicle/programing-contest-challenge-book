package programing.contest.chapter2;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Poj3669MeteorShowerKesota826 {

	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};

	static int n;

	static int[][] past = new int[400][400]; // 既に通過した場所
	static int[][] map = new int[400][400]; // 隕石に破壊される時間

//	static final int INF = 10000;
	static final int INF = 10000000;

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

		// 初期化
		for (int i=0; i<400; i++) {
			for (int j=0; j<400; j++) {
				map[i][j] = INF;
			}
		}

//		Scanner sc = new Scanner(new File("src/contest/poj/p3669/in.txt"));
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		int x = 0;
		int y = 0;
		int t = 0;

		// 各地点の隕石到達時刻を調べる
		for (int i=0; i<n; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			t = sc.nextInt();

			if (map[x][y] > t) {
				map[x][y] = t;
			}
			for (int j=0; j<4; j++) {
				if (x + dx[j] >= 0 && y + dy[j] >= 0 && map[x + dx[j]][y + dy[j]] > t) {
					map[x + dx[j]][y + dy[j]] = t;
				}
			}
		}

		Queue<Point> q = new LinkedList<Point>();

		x = 0;
		y = 0;
		t = 0;

		if (map[0][0] == 0) {
			System.out.println("-1");
			return;
		} else if (map[0][0] == INF) {
			System.out.println("0");
			return;
		}
		q.add(new Point(x,y,t));

		Point p;

		while((p = q.poll()) != null) {

			if (past[p.x][p.y] == 1) {
				continue;
			}
			past[p.x][p.y] = 1;

			for (int i=0; i<4; i++) {
				x = p.x + dx[i];
				y = p.y + dy[i];
				t = p.t + 1;

				if(x >= 0 && y >= 0) {
					if (map[x][y] == INF) {
						System.out.println(t);
						return;
					} else if (map[x][y] <= t) {
						// 何もしない
					} else if (map[x][y] > t) {
						q.add(new Point(x,y,t));
					}

				}

			}
		}

		System.out.println("-1");

	}
}