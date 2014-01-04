/**
  Meteor Shower
  http://poj.org/problem?id=3669
*/
#include <cstdio>
#include <queue>

using namespace std;

const static int T_MAX = 1000 + 1; // 0 <= T_i <= 1000
const static int F_MAX = 300 + 1 + 1;  // 0 <= X_i,Y_i <= 300

struct PointInfo {
    int meteor_time; // when this point is destroyed (min(T_i))
    bool visited;    // if Bessie has visited this point before
    PointInfo() : meteor_time(T_MAX), visited(false) {}
};
static PointInfo field[F_MAX][F_MAX];

static inline bool in_field(int x, int y){
    return 0 <= x && x < F_MAX && 0 <= y && y < F_MAX;
}

struct State {
    const int x, y, t; // Bessie is at position (x,y) at time t
    State(int x, int y, int t): x(x), y(y), t(t) {}
};

static inline bool is_safe(const State& s) {
    return s.t < field[s.x][s.y].meteor_time;
}
static inline bool is_goal(const State& s) {
    return field[s.x][s.y].meteor_time == T_MAX;
}

// move vectors
static const int dx[] = {1,  0, -1,  0,  0};
static const int dy[] = {0,  1,  0, -1,  0};

// for debug
inline void print(const State& s){
    printf("(%d,%d,%d, %s, %s)", s.x, s.y, s.t, is_safe(s) ? "safe" : "   x", is_goal(s) ? "goal" : "   x");
}
inline void print(const PointInfo& pi){
    printf("[mt:%4d, v:%s]", pi.meteor_time, pi.visited ? "true " : "false");
}

int main(void){
    // Read Line 1: A single integer: M
    int M; scanf("%d", &M);  

    // Update the field with meteor destruction times: T_i
    for(int m = 0; m < M; m++) {
        // Read Lines 2..M+1: Line i+1 containes three space-separated integers: X_i, Y_i, and T_i
        int X_i, Y_i, T_i; scanf("%d %d %d", &X_i, &Y_i, &T_i);
        
        //printf("meteor strikes at (%d, %d) at %d\n", X_i, Y_i, T_i);//debug
        for(int i = 0; i < 5; i++){
            const int x = X_i + dx[i]; const int y = Y_i + dy[i];
            if (in_field(x,y)) {
                //printf(" -> destroys (%3d, %3d: %4d)", x, y, field[x][y].meteor_time); //debug
                field[x][y].meteor_time = (T_i < field[x][y].meteor_time) ? T_i : field[x][y].meteor_time;
                //printf(" => (%3d, %3d: %4d)\n", x, y, field[x][y].meteor_time); //debug
            } 
        }
    }
    
    // search the goal state by DFS
    int answer = -1; // = unreachable
    queue<State> q;
    const State start(0,0,0);
    field[start.x][start.y].visited = true;
    q.push(start);

    // shortcut    
    if (!is_safe(start)) {
        goto END;
    }
    if (is_goal(start)){
        answer = start.t; // 0
        goto END;
    }

    while(!q.empty()){
        const State s = q.front(); q.pop();
        //printf("pop(): "); print(s); print(field[s.x][s.y]); putchar('\n'); //debug
        
        // Generate 4 movable points and select possible next state, and push them into the queue
        for (int i = 0; i < 4; i++){
            const int next_x = s.x + dx[i]; const int next_y = s.y + dy[i]; const int next_t = s.t + 1;
            if (!in_field(next_x, next_y)){
                continue;
            }
            
            const State next_s(next_x, next_y, next_t);
            if (is_goal(next_s)) {
                //printf(" GOAL: "); print(next_s); print(field[next_s.x][next_s.y]); putchar('\n'); //debug
                answer = next_s.t;
                goto END;
            } 
            if (!is_safe(next_s) || field[next_s.x][next_s.y].visited) {
                //printf(" xxxx: "); print(next_s); print(field[next_s.x][next_s.y]); putchar('\n'); //debug
                continue;
            }
            //printf(" push: "); print(next_s); print(field[next_s.x][next_s.y]); putchar('\n'); //debug
            field[next_s.x][next_s.y].visited = true;
            q.push(next_s);
        }
    }

    END:
        printf("%d\n", answer);
    
    return 0;
}
