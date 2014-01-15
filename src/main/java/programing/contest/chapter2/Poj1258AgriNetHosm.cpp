/**
   Agri-Net
   http://poj.org/problem?id=1258
*/
#include <iostream>
#include <utility>
#include <vector>
#include <queue>

using namespace std;

// connectivity matrix 
const static int N_MAX = 100;
static int E[N_MAX][N_MAX];   
static bool is_used[N_MAX];

typedef pair<int,int> Edge;
struct MoreDistanceComparator{
    bool operator()(const Edge& e1, const Edge& e2) const{
        return E[e1.first][e1.second] > E[e2.first][e2.second];
    }
};
typedef priority_queue<Edge, vector<Edge>, MoreDistanceComparator> EdgePriorityQueue;

int main(void){
    int n;
    while(cin >> n) {
        // Read conectivity matrix
        for(int i = 0; i < n; i++){
            is_used[i] = false;
            for(int j = 0; j < n; j++){
                cin >> E[i][j];
            }
        }

        // Prim
        EdgePriorityQueue q;
        int v = 0;           // begin with farm 0
        is_used[v] = true;
        int total_cost = 0;  // total cost for covering all firms
        for(int k = 0; k < n; k++){
            // Find all distances from farms v
            for(int i = 0; i < n; i++) {
                if(!is_used[i]){
                    q.push(Edge(v,i));
                }
            }
            // Get the nearest farm
            while(!q.empty()){
                Edge e = q.top(); q.pop();                
                if(!is_used[e.second]){
                    v = e.second; 
                    is_used[v] = true;
                    total_cost += E[e.first][v];
                    break;
                }
            }
        }
        cout << total_cost << endl;
    }
    return 0;
}
