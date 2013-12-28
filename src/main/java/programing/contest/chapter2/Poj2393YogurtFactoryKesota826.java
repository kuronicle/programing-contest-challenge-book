package programing.contest.chapter2;

import java.io.IOException;
import java.util.Scanner;

public class Poj2393YogurtFactoryKesota826 {

	static int N; // N週間
	static int S; // 倉庫コスト

	static int[] C; // 生産コスト
	static int[] Y; // 必要ヨーグルト

	// 生産量
	static int[] P;


	public static void main(String[] args) throws IOException {
		// 開始時の時間
//		Date start;
//		Date end;
//		long time;
//		start = new Date();

//		Scanner sc = new Scanner(new File("src/contest/poj/p2393/in.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = sc.nextInt();

		C = new int[N];
		Y = new int[N];
		P = new int[N];

		long ans = 0;

		for (int i=0; i<N; i++) {
			C[i] = sc.nextInt();
			Y[i] = sc.nextInt();
		}

		for (int i=0; i<N; i++) {
			int minCost = C[i];

			for (int j = i; j>=0; j--) {
				if (minCost > C[j]+S*(i-j)) {
					minCost = C[j]+S*(i-j);
				}
			}
			ans += minCost*Y[i];
		}


		System.out.println(ans);

//		// 終了時の時間
//		end = new Date();
//		time = end.getTime() - start.getTime();
//		System.out.println("time:" + time);
	}
}