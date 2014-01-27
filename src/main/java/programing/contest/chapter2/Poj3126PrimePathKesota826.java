package programing.contest.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Poj3126PrimePathKesota826 {
	static Date start;
	static Date end;
	static long time;

	static boolean[] primeList;

	public static void main(String[] args) throws FileNotFoundException {

		//先に素数リスト作成
		primeList = new boolean[10000];

		for (int i=2; i<primeList.length; i++) {
			primeList[i] = true;
		}

		for (int i=2; i<primeList.length; i++) {
			if (primeList[i]) {
				for (int j=i*2; j<primeList.length; j=j+i) {
					primeList[j] = false;
				}
			}
		}

		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
		//		Scanner sc = new Scanner(System.in);

		int c = sc.nextInt();

		for (int i=0; i<c; i++) {
			int s = sc.nextInt();
			int g = sc.nextInt();
			solve(s,g);
		}

	}

	static void solve (int s, int g) {
		Queue<Vec> q = new LinkedList<Vec>();
		boolean[] pastList = new boolean[10000];

		pastList[s] = true;
		q.add(new Vec(s,0));

		int n;
		int n1;
		int n2;
		int n3;
		int n4;

		Vec v;
		int cost;

		while(!q.isEmpty()) {
			v = q.poll();
			n = v.a;
			cost = v.b;

			if (n < 1000) {
				continue;
			}

			if (n == g) {
				System.out.println(cost);
				return;
			}

			n1 = n/1000;
			n2 = (n - n1*1000)/100;
			n3 = (n - n1*1000 - n2*100)/10;
			n4 = n - n1*1000 - n2*100 - n3*10;

			int temp;
			for (int i=0; i<10; i++) {
				// n1
				temp = n - n1*1000 + i*1000;
				if (!pastList[temp] && primeList[temp]) {
					pastList[temp] = true;
					q.add(new Vec(temp, cost+1));
				}

				// n2
				temp = n - n2*100 + i*100;
				if (!pastList[temp] && primeList[temp]) {
					pastList[temp] = true;
					q.add(new Vec(temp, cost+1));
				}

				//n3
				temp = n - n3*10 + i*10;
				if (!pastList[temp] && primeList[temp]) {
					pastList[temp] = true;
					q.add(new Vec(temp, cost+1));
				}

				//n4
				temp = n - n4*1 + i*1;
				if (!pastList[temp] && primeList[temp]) {
					pastList[temp] = true;
					q.add(new Vec(temp, cost+1));
				}

			}

		}

		System.out.println("Impossible");
	}

	static class Vec {
		public int a;
		public int b;

		public Vec(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

}