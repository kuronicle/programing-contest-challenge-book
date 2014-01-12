package programing.contest.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Poj3614SunscreenKesota826 {
	static Date start;
	static Date end;
	static long time;

	static int count = 0;

	static int[] map;

	public static void main(String[] args) throws FileNotFoundException {
		// 開始時の時間
		start = new Date();

		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
		//		Scanner sc = new Scanner(System.in);

		int C = sc.nextInt();
		int L = sc.nextInt();

		int min;
		int max;
		List<Vec> cList = new ArrayList<Vec>();

		for (int i=0; i<C; i++) {
			min = sc.nextInt();
			max = sc.nextInt();

			cList.add(new Vec(min,max));

		}

		int num;
		int spf;
		List<Vec> lList = new ArrayList<Vec>();

		for (int i=0; i<L; i++) {
			spf = sc.nextInt();
			num = sc.nextInt();

			lList.add(new Vec(num,spf));

		}

		Collections.sort(cList, new OriginalComparator());
		Collections.sort(lList, new OriginalComparator());

		Vec lotion = null;
		Vec cow = null;
		while (lList.size() > 0) {
			lotion = lList.remove(0);

			num = lotion.a;
			spf = lotion.b;


			for (int i=0; i<cList.size(); i++) {
				if (num == 0) {
					break;
				}

				cow = cList.get(i);

				min = cow.a;
				max = cow.b;

				if (min <= spf && spf <= max) {
					cList.remove(i);
					num--;
					i--;
				}
			}

		}

		System.out.println(C - cList.size());


		//		// 終了時の時間
		//		end = new Date();
		//		time = end.getTime() - start.getTime();
		//		System.out.println("time:" + time);
		//		System.out.println("count:" + count);
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