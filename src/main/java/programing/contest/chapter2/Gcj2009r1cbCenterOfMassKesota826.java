package programing.contest.chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;

public class Gcj2009r1cbCenterOfMassKesota826 {
	static Date start;
	static Date end;
	static long time;

	static boolean[] primeList;

	static final double DIFF = 1e-12;

	public static void main(String[] args) throws FileNotFoundException {

		// 問題の読み込み
//		Scanner sc = new Scanner(new File("src/B-small-practice.in"));
		Scanner sc = new Scanner(new File("src/B-large-practice.in"));
//		sc = new Scanner(System.in);

		int T = sc.nextInt();



		for (int i=0; i<T; i++) {
			int N = sc.nextInt();

			double x = 0;
			double y = 0;
			double z = 0;
			double vx = 0;
			double vy = 0;
			double vz = 0;

			for (int j=0; j<N; j++) {
				x += sc.nextDouble();
				y += sc.nextDouble();
				z += sc.nextDouble();
				vx += sc.nextDouble();
				vy += sc.nextDouble();
				vz += sc.nextDouble();
			}

			x = x/N;
			y = y/N;
			z = z/N;
			vx = vx/N;
			vy = vy/N;
			vz = vz/N;

			solve(x,y,z,vx,vy,vz,i+1);
		}

//		solve(-6,1,0,1,0,0,1);
//		solve(-1,-1,-1,1,1,1,1);
//		solve(5/4,14/4,1,1,-6/4,2/4,3);

	}

	static void solve (double x, double y, double z, double vx, double vy, double vz, int X) {
		// 最初から原点の場合
		if (x == 0 && y == 0 && z == 0) {
			print(0,0,X);
			return;
		}
		// 速度が完全に0の場合
		if (vx == 0 && vy == 0 && vz == 0) {
			print(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)+Math.pow(z,2)),0,X);
			return;
		}

		double t1 = 0;
		double t2 = 3000000;

		double d1 = 0;
		double d2 = 0;

		while(true) {
			d1 = Math.sqrt(Math.pow((x + vx*t1),2)+Math.pow((y + vy*t1),2)+Math.pow((z + vz*t1),2));
			d2 = Math.sqrt(Math.pow((x + vx*t2),2)+Math.pow((y + vy*t2),2)+Math.pow((z + vz*t2),2));

//			System.out.println(t1 +":"+ t2 +":"+ d1 +":" + d2);

			if (d1-d2 < DIFF && d2-d1 < DIFF) {
				print(d1, t1, X);
				return;
			}

			if (d1 > d2) {
				t1 = (t1 + t2)/2;
			}
			if (d1 < d2) {
				t2 = (t1 + t2)/2;
			}

		}

	}

	static void print(double d, double t, int X) {
		DecimalFormat df1 = new DecimalFormat("0.00000000");


		System.out.println("Case #" + X + ": " + df1.format(d) + " " + df1.format(t));

	}

}