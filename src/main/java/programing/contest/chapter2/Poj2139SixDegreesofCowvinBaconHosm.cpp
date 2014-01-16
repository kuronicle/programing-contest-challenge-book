/**
   Six Degrees of Cowvin Bacon
   http://poj.org/problem?id=2139
 */
#include <iostream>
#include <algorithm>

using namespace std;

const static int MAX_N = 300;
const static int INF = 99999; // or numeric_limits<int>::max();

static int d[MAX_N][MAX_N];   // d[i][j]: distance from cow_i to cow_j
static int buf[MAX_N];        // for reading input

int main(void){
    // N: the number of cows, M: the number of movies
    int N, M; cin >> N >> M;

    // Initialize distances
    for(int i = 0; i < N; ++i){
        for(int j = 0; j < N; ++j){
            d[i][j] = (i==j) ? 0 : INF;
        }
    }

    // Read each Movie
    for(int i = 0; i < M; ++i){
        int n; cin >> n;
        for(int j = 0; j < n; j++){
            cin >> buf[j];
            --buf[j]; // adjust cow number (eg. 1 -> 0)
        }
        for(int j = 0; j < n; j++){
            for(int k = j + 1; k < n; k++){
                d[buf[j]][buf[k]] = 1;
                d[buf[k]][buf[j]] = 1;
            }
        }
    }

    // Warshall-Floyd
    for(int n = 0; n < N; n++){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                d[i][j] = min(d[i][j], d[i][n] + d[n][j]);
            }
        }
    }
    
    // Calculate the shortest mean degree 
    int ret = INF;
    for(int i = 0; i < N; i++){
        int a = 0; // degree sum of cow_i
        for(int j = 0; j < N; j++){
            a += d[i][j]; // if i == j, d[i][j] == 0
        }
        ret = min(ret, a);
    }

    cout << (ret * 100) / (N - 1) << endl;
    
    return 0;
}
