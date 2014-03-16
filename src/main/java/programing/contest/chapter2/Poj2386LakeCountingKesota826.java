package programing.contest.chapter2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Poj2386LakeCountingKesota826 {

	static int N;
	static int M;
	static char[][] map;

	public static void main(String[] args) throws FileNotFoundException {
		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
		//		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			N = sc.nextInt();
			M = sc.nextInt();

			map = new char[N][M];

			for (int i=0; i<N; i++) {
				map[i] = sc.next().toCharArray();
			}


			int count = 0;

			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (map[i][j] == 'W') {
						count ++;
						fill(i,j);
					}
				}
			}
			
			System.out.println(count);

		}
	}

	static void fill (int n, int m) {
		map[n][m] = '.';
		
		for (int i=-1; i<=1; i++) {
			for (int j=-1; j<=1; j++) {
				if (0 <= n+i && n+i < N && 0 <= m+j && m+j < M) {
					if (map[n+i][m+j] == 'W') {
						fill(n+i, m+j);
					}
				}
			}
		}
	}

}