/**
  Cow Bowling
  http://poj.org/problem?id=3176
 */
#include <cstdio>
#include <algorithm>

using namespace std;

static const int N_MAX = 350;

static int p[N_MAX][N_MAX];
static int N; // 1 <= N <= 350

static const int INVALID = -1;
static int dp[N_MAX][N_MAX];

// highest score starting from p[i][j]
static int highest_score(int i, int j){
    if (dp[i][j] != INVALID) {
        return dp[i][j];
    }
    if (i == N - 1) {
        return p[i][j];
    }
    const int ret = p[i][j] + max(highest_score(i+1, j), highest_score(i+1, j+1));
    dp[i][j] = ret;
    return ret;
}

int main(void){
    scanf("%d", &N);
    for(int i = 0; i < N; i++){
        for(int j = 0; j <= i; j++){
            // Read input
            scanf("%d", &p[i][j]);
            // Initialize DP table
            dp[i][j] = INVALID;
        }
    }
    printf("%d\n", highest_score(0,0));
    return 0;
}
