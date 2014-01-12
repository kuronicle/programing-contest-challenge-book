package programing.contest.chapter2;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

public class Poj2229SumsetsKesota826 {
	static Date start;
	static Date end;
	static long time;

	static int count = 0;

	static int n;
	static int[] map;

	public static void main(String[] args) throws FileNotFoundException {
		// 開始時の時間
		start = new Date();

		// 問題の読み込み
//		Scanner sc = new Scanner(new File("src/in.txt"));
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		map = new int[n+1];
		map[0] = 1;

		for (int i=1; i<n+1; i++) {
			count++;
			if(i%2 == 1) {
				map[i] = map[i-1];
			} else {
				map[i] = map[i-1] + map[i/2];
				if (map[i] > 1000000000) {
					map[i] -= 1000000000;
				}
			}
		}

		System.out.println(map[n]);

//		// 終了時の時間
//		end = new Date();
//		time = end.getTime() - start.getTime();
//		System.out.println("time:" + time);
//		System.out.println("count:" + count);
	}

}