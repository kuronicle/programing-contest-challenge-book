package programing.contest.chapter2;


public class Poj2139SixDegreesofCowvinBaconMashihara {
	public static void main(String[] args) {
/*
 		//ローカルでファイル入力→失敗
 		Scanner sc = null;
		try{
			File file = new File("POJ2139in.txt")//ファイルが指定できない
			 sc = new Scanner(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		int a=sc.nextInt();
		System.out.println(a);
*/

		//サンプル入力
		//1行目
		int N=4;
		int M=2;
		int[][] dp = new int[N][N];
		int[][] movie = new int[M][N+1];

/*		int[] movie1 = {1,2,3};
		int[] movie2 = {3,4};

		2行目
		for(int movieNo=0;movieNo<M;j++){
			movie[movieNo][0] =sc.nextInt();
			for(int i=1;i<movieCow+1;i++){
				movie[movieNo][i] = sc.nextInt();

		}
*/
		//movie[movieNo][0]は映画の出演者数
		movie[0][0]=3;movie[0][1]=1;movie[0][2]=2;movie[0][3]=3;
		movie[1][0]=2;movie[1][1]=3;movie[1][2]=4;

		//入力
		for(int movieNo=0;movieNo<M;movieNo++){
			for(int i=1;i<N;i++){
				for(int j=i+1;j<movie[movieNo][0]+1;j++){
					dp[movie[movieNo][i]-1] [movie[movieNo][j]-1] =1;
					dp[movie[movieNo][j]-1] [movie[movieNo][i]-1] =1;
				}
			}
		}
		//各牛から全ての牛へのdegreeマップを作成（dp）

		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(i !=j){
					for(int k=0;k<N;k++){
						if(k!=i || k!=j){
							if(dp[i][j] > (dp[i][k]+dp[k][j])){
								dp[i][j]=dp[i][k]+dp[k][j];
							}
						}
					}
				}
			}
		}
		//各牛のdegree和を求め、最も少ないdegree和を出す
		int minSum=9999;
		int[] sum = new int[N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				sum[i] += dp[i][j];
			}
			if(minSum >sum[i]){
				minSum = sum[i];
			}
		}
		System.out.println(minSum*100/N-1);
	}


}
