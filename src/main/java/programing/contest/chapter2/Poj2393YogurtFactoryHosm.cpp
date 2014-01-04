/**
  Yogurt factory
  http://poj.org/problem?id=2393
*/
#include <iostream>
#include <algorithm>

#define C_MAX 5000

using namespace std;

int main(void){
  int N, S, C_i, Y_i;
  unsigned long long total_cost = 0;
  int prev_cost = C_MAX + 1;
  int cost = 0;

  cin >> N >> S;
  for(int i = 1; i <= N; i++){
    cin >> C_i >> Y_i;
    cost = min(C_i, prev_cost + S);
    total_cost += cost * Y_i;
    prev_cost = cost;
  }
  cout << total_cost << endl;

  return 0;
}
