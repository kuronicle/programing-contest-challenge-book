package programing.contest.chapter2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Poj1979RedAndBlackKesota826 {

	static int N;
	static int M;
	static char[][] map;
	
	static int[] dn = {-1,0,1,0};
	static int[] dm = {0,-1,0,1};
	
	static int count;

	public static void main(String[] args) throws FileNotFoundException {
		// 問題の読み込み
		Scanner sc = new Scanner(new File("src/in.txt"));
		//		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			M = sc.nextInt();
			N = sc.nextInt();

			map = new char[N][M];

			for (int n=0; n<N; n++) {
				map[n] = sc.next().toCharArray();
			}

			if(N == 0 && M == 0) {
				return;
			}
			
			count = 0;
			for (int n=0; n<N; n++) {
				for (int m=0; m<M; m++) {
					if (map[n][m] == '@') {
						solve(n,m);
					}
				}
			}
			
			System.out.println(count);
		}
	}

	static void solve(int n, int m) {
		count++;
		map[n][m] = '#';
		
		for (int i=0; i<4; i++) {
			if (0 <= n+dn[i] && n+dn[i] < N && 0 <= m+dm[i] && m+dm[i] < M) {
				if (map[n+dn[i]][m+dm[i]] == '.') {
					solve(n+dn[i], m+dm[i]);
				}
			}
		}
	}

}