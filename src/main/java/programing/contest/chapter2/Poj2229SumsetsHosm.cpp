/**
   Sumsets
   http://poj.org/problem?id=2229
 */
#include <cstdio>
using namespace std;

static const int N_MAX = 1000000;
static unsigned long dp[N_MAX + 1];

int main(void){
    int N; scanf("%d", &N);

    // Initialize DP table
    dp[0] = 1;
    for (int i = 1; i < N + 1; ++i) {
        // f(n) = f(n-1) if n is odd
        // f(n) = f(n-1) + f(n/2) if n is even
        dp[i] = ((i % 2) ? dp[i-1] : dp[i-1] + dp[i/2]) % 1000000000;
    }
    printf("%lu\n", dp[N]);
    return 0;
}
