package programing.contest.chapter2;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Poj1065WoodenStickKesota826 {
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

		int T = sc.nextInt();

		int N;
		List<Stick> list;// Stickをソートするためのリスト
		Stick s;

		for (int i=0; i<T; i++) {
			N = sc.nextInt();
			list = new ArrayList<Stick>();

			for (int j=0; j<N; j++) {
				int l = sc.nextInt();
				int w = sc.nextInt();
				s = new Stick(l,w);
				list.add(s);
			}

			int fw = 0;
			int sw = 0;
			Queue<Integer> q = new PriorityQueue<Integer>(6000);
			List<Integer> temp = new ArrayList<Integer>();

			Collections.sort(list, new StickComparator());

			for(int j=0; j<list.size(); j++) {
				s = list.get(j);
				if (q.size() == 0) {
					q.add(s.w);
				} else {
					fw = q.peek();

					// 新しい板のwが、今セットされた板群の最小のwより小さい場合
					if (fw > s.w) {
						q.add(s.w);
						continue;
					}

					int flag = 0;
					temp.clear();
					// 新しい板のwが、今セットされた板郡のwの中で一番近い板と入れ替える
					while (q.size() > 1) {

						fw = q.poll(); //チェック済みは除去
						sw = q.peek(); //今回チェック分はキューに残す
						if (sw > s.w) {
							// fwを捨てて、新しい板のwと入れ替える。
							for (int k=0; k<temp.size(); k++) {
								q.add(temp.get(k));
							}
							q.add(s.w);
							flag = 1;
							break;
						}
						// チェック済みをリスト化する
						temp.add(fw);
					}

					// 新しい板のwが、今のセットされた板群のなかで一番大きい場合
					if (flag == 0) {
						fw = q.poll();
						for (int k=0; k<temp.size(); k++) {
							q.add(temp.get(k));
						}
						q.add(s.w);
					}

				}
			}

			System.out.println(q.size());

		}

//		// 終了時の時間
//		end = new Date();
//		time = end.getTime() - start.getTime();
//		System.out.println("time:" + time);
//		System.out.println("count:" + count);
	}

	public static class Stick {
		public int l;
		public int w;

		public Stick(int l, int w) {
			this.l = l;
			this.w = w;
		}
	}

	public static class StickComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			// TODO 自動生成されたメソッド・スタブ
			Stick s1 = (Stick) o1;
			Stick s2 = (Stick) o2;

			if (s1.l > s2.l) {
				return 1;
			} else if (s1.l == s2.l) {
				if (s1.w > s2.w) {
					return 1;
				} else if (s1.w == s2.w) {
					return 0;
				} else {
					return -1;
				}
			} else {
				return -1;
			}

		}

	}

}