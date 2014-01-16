/**
   Agri-Net Kruskal
   http://poj.org/problem?id=1258
*/
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/**
 *  Union-Find Tree
 *  http://www.prefield.com/algorithm/container/union_find.html
 */
struct UnionFind {
    vector<int> data;
    UnionFind(int size) : data(size, -1) { }
    bool unionSet(int x, int y) {
        x = root(x); y = root(y);
        if (x != y) {
            if (data[y] < data[x]) swap(x, y);
            data[x] += data[y]; data[y] = x;
        }
        return x != y;
    }
    bool findSet(int x, int y) {
        return root(x) == root(y);
    }
    int root(int x) {
        return data[x] < 0 ? x : data[x] = root(data[x]);
    }
    int size(int x) {
        return -data[root(x)];
    }
};
/** Union-Find Tree */

struct Edge {
    int u, v, cost;
    Edge(int u, int v, int cost) : u(u), v(v), cost(cost) {}
};
struct EdgeCostComparator {
    bool operator()(const Edge& e1, const Edge& e2) {
        return e1.cost < e2.cost;
    }
};
typedef vector<Edge> Edges;

int main(void){
    int n;
    while(cin >> n) {
        Edges edges; edges.reserve(n*n); 
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int c; cin >> c;
                edges.push_back(Edge(i, j, c));
            }
        }

        // Kruskal
        sort(edges.begin(), edges.end(), EdgeCostComparator());
        UnionFind uf(n);
        int total_cost = 0;
        for(Edges::iterator it = edges.begin(); it != edges.end(); ++it){
            if (uf.root(it->u) != uf.root(it->v)) {
                uf.unionSet(it->u, it->v);
                total_cost += it->cost;
            }
        }
        cout << total_cost << endl;
    }
    return 0;
}
